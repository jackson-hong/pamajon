'use strict';
let stompClient = null;
let selectedUser = null;
let userName = $("#from").val();

function setConnected(connected) {
    $("#from").prop("disabled", connected);
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#sendmessage").show();
    } else {
        $("#sendmessage").hide();
    }
}

function connect() {
    userName = $("#from").val();

    if (userName == null || userName === "") {
        alert('Please input a nickname!');
        return;
    }
    if(userName!="운영자"){
        selectedUser="운영자";
    }
    $.post('/pamajon/chat/user-connect',
        { username: userName },
        function (remoteAddr, status, xhr) {
            var socket = new SockJS('/pamajon/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({ username: userName, userId: "test" }, function () {
                stompClient.subscribe('/topic/active', function () {
                    if (userName == "운영자") {
                        updateUsers(userName);
                    }
                });
                //메세지를 서버에 보낸후 응답을 받음.
                stompClient.subscribe('/user/queue/messages', function (output) {
                    if(selectedUser!="운영자") {
                        //이 케이스는 운영자가 다른 사람에게 메세지를 받는 케이스

                        //selectedUser 외 다른 사람이 메세지를 보냈을경우 채팅방에서 보여주지않음
                        if(JSON.parse(output.body).from==selectedUser) {
                            showMessage(createTextNode(JSON.parse(output.body)));

                         //내가 운영자면 내가 보낸 매세지 볼 수 있어야함.
                        } else if(JSON.parse(output.body).from=="운영자"){
                            showMessage(createTextNode(JSON.parse(output.body)));
                        }
                    } else if(selectedUser=="운영자"){
                        //운영자가 아닌 일반 유저 통신은 운영자만이랑 이뤄짐으로 그냥 다 볼 수있어야함.
                            showMessage(createTextNode(JSON.parse(output.body)));
                    }
                });
                //방에 입장했을때 메세지 리스트를 보여줌.
                stompClient.subscribe('/user/queue/messageList', function (messageList) {
                    if (messageList.length != 0) {
                        reconstructMessage(JSON.parse(messageList.body));
                    }
                });
                //대화방에 입장한 후 보게될 메세지를 서버로부터 응답 받음.
                stompClient.subscribe('/user/queue/entrymessage', function (result) {
                    entryHandler(result.body);
                });
                if(selectedUser=="운영자"){
                    stompClient.send("/app/user/messageList", { 'sender': userName },
                    JSON.stringify({ 'from': userName, 'recipient': selectedUser }));
                }
                sendConnection(' connected to server');
                setConnected(true);
            }, function (err) {
                alert('error' + err);
            });
        }).done(function () {
            // alert('Request done!');
        }).fail(function (jqxhr, settings, ex) {

        }
        );
}

function disconnect() {
    if (stompClient != null) {
        $.post('/pamajon/chat/user-disconnect',
            { username: userName },
            function () {
                sendConnection(' disconnected from server');
                stompClient.disconnect(function () {

                    setConnected(false);
                });
            }).done(function () {
            }).fail(function (jqxhr, settings, ex) {

            }
            );
    }
}

function sendConnection(message) {
    var text = userName + message;
    sendBroadcast({ 'from': 'server', 'text': text });
    updateUsers(userName);
}

function sendBroadcast(json) {
    stompClient.send("/app/broadcast", {}, JSON.stringify(json));
}

function send() {
    var text = $("#write_msg").val();
    if (userName == "운영자") {
        if (selectedUser == null) {
            alert('Please select a user.');
            return;
        }
    } else {
        selectedUser = "운영자";
    }
    stompClient.send("/app/chat", { 'sender': userName },
        JSON.stringify({ 'from': userName, 'text': text, 'recipient': selectedUser }));
    $("#write_msg").val("");
}

function createTextNode(messageObj) {

    let fromTo = messageObj.from;
    let addTo = fromTo;
    //fromTo 와 userName 이 다른경우 받은 메세지임.
    if (userName != fromTo) {
        return `<div class="incoming_msg">
                    <div class="incoming_msg_img"><img
                            src="https://ptetutorials.com/images/user-profile.png"
                            alt="sunil"></div>
                    <div class="received_msg">
                        <h6>${messageObj.from}</h6>
                        <div class="received_withd_msg">
                            <p>${messageObj.text}</p>
                            <span class="time_date">${messageObj.time}</span></div>
                    </div>
                </div>
             `
    } else {
        return `
                <div class="outgoing_msg">
                    <div class="sent_msg">
                        <p>${messageObj.text}</p>
                        <span class="time_date">${messageObj.time}</span></div>
                </div>
        `
    }

}

function showMessage(message) {

    $(".msg_history").html($(".msg_history").html() + message);

}

function clearMessages() {
    $("#content").html("");
    $("#clear").hide();
}

function setSelectedUser(username) {

    selectedUser = username;

    stompClient.send("/app/admin/messageList", { 'sender': userName },
        JSON.stringify({ 'from': userName, 'recipient': selectedUser }));

    stompClient.send("/app/entry", { 'sender': userName },
        JSON.stringify({ 'from': userName, 'recipient': selectedUser }));

}

function reconstructMessage(messageList) {

    let str = "";
    let msgHistory = document.querySelector(".msg_history");

    while (msgHistory.hasChildNodes()) {
        msgHistory.removeChild(msgHistory.firstChild);
    }
    for (let i = 0; i < messageList.length; i++) {

        if(selectedUser=="운영자"){

            if(messageList[i].from == "운영자"){
                str += `
                <div class="incoming_msg">
                    <div class="incoming_msg_img"><img
                            src="https://ptetutorials.com/images/user-profile.png"
                            alt="sunil"></div>
                    <div class="received_msg">
                        <h6>${messageList[i].from}</h6>
                        <div class="received_withd_msg">
                            <p>${messageList[i].text}</p>
                            <span class="time_date">${messageList[i].time}</span></div>
                    </div>
                </div>
            `
            } else {
                str += `<div class="outgoing_msg">
                    <div class="sent_msg">
                        <p>${messageList[i].text}</p>
                        <span class="time_date">${messageList[i].time}</span></div>
                </div>
                `
            }

        } else if(selectedUser!="운영자") {

            if (messageList[i].from == "운영자") {

                str += `<div class="outgoing_msg">
                    <div class="sent_msg">
                        <p>${messageList[i].text}</p>
                        <span class="time_date">${messageList[i].time}</span></div>
                </div>`
            } else {

                str += `
            <div class="incoming_msg">
                    <div class="incoming_msg_img"><img
                            src="https://ptetutorials.com/images/user-profile.png"
                            alt="sunil"></div>
                    <div class="received_msg">
                        <h6>${messageList[i].from}</h6>
                        <div class="received_withd_msg">
                            <p>${messageList[i].text}</p>
                            <span class="time_date">${messageList[i].time}</span></div>
                    </div>
                </div>
            `
            }
        }
        $(".msg_history").html(str);

    }

}
function updateUsers(userName) {

    if (userName == "운영자") {
        let activeUserSpan = $(".inbox_chat");
        let index;
        activeUserSpan.html('');
        let url = '/pamajon/chat/active-user-except/' + userName;

        $.ajax({
            type: 'GET',
            url: url,
            // data: data,
            dataType: 'json', // ** ensure you add this line **
            success: function (userList) {
                if (userList.length == 0) {
                    activeUserSpan.html('<p align="center">접속한 유저가 없습니다.</p>');
                } else {
                    for (index = 0; index < userList.length; ++index) {
                        if (userList[index] != userName) {
                            activeUserSpan.html(activeUserSpan.html() + createUserNode(userList[index]));
                        }
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error occurred");
            }
        });
    }
}
function entryHandler(result) {

    let entryNode = `<span class="time_date" style="color:black" align="center">${result}</span>`
    showMessage(entryNode);
}

function createUserNode(username) {

    return `
                <div class="chat_list active_chat" href="javascript:void(0)" onclick="setSelectedUser(`+ '\'' + username + '\'' + `)">
                    <div class="chat_people">
                        <div class="chat_ib">
                            <h5>${username}<span class="chat_date">2021-01-14</span></h5>
                        </div>
                    </div>
                </div>
         `

}
