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
    $.post('/pamajon/chat/user-connect',
        { username: userName },
        function (remoteAddr, status, xhr) {
            var socket = new SockJS('/pamajon/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({ username: userName }, function () {
                console.log("실행");
                stompClient.subscribe('/queue/messages', function (output) {
                    alert(output);
                    showMessage(createTextNode(JSON.parse(output.body)));
                });
                stompClient.subscribe('/topic/active', function () {
                    updateUsers(userName);
                });
                stompClient.subscribe('/user/queue/messages', function (output) {
                    alert(output);
                    showMessage(createTextNode(JSON.parse(output.body)));
                });
                sendConnection(' connected to server');
                setConnected(true);
            }, function (err) {
                alert('error' + err);
            });
        }).done(function () {
            // alert('Request done!');
        }).fail(function (jqxhr, settings, ex) {
            console.log('failed, ' + ex);
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
                    console.log('disconnected...');
                    setConnected(false);
                });
            }).done(function () {
            }).fail(function (jqxhr, settings, ex) {
                console.log('failed, ' + ex);
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
    if (selectedUser == null) {
        alert('Please select a user.');
        return;
    }
    stompClient.send("/app/chat", {'sender': userName},
        JSON.stringify({ 'from': userName, 'text': text, 'recipient': selectedUser }));
    $("#write_msg").val("");
}

function createTextNode(messageObj) {

    var classAlert = 'alert-info';
    var fromTo = messageObj.from;
    var addTo = fromTo;
    if (userName == messageObj.from) {
        fromTo = messageObj.recipient;
        addTo = 'to: ' + fromTo;
    }
    if (userName != messageObj.from && messageObj.from != "server") {
        classAlert = "alert-warning";
    }
    if (messageObj.from != "server") {
        addTo = '<a href="javascript:void(0)" onclick="setSelectedUser(\'' + fromTo + '\')">' + addTo + '</a>'
    }
    return '<div class="row alert ' + classAlert + '"><div class="col-md-8">' +

        messageObj.text +
        '</div><div class="col-md-4 text-right"><small>[<b>' +
        addTo +
        '</b> ' +
        messageObj.time +
        ']</small>' +
        '</div></div>';
}

function showMessage(message) {

    $("#content").html($("#content").html() + message);
    $("#clear").show();

}

function clearMessages() {
    $("#content").html("");
    $("#clear").hide();
}

function setSelectedUser(username) {
    selectedUser = username;

    $("#selectedUser").html(selectedUser);
    if ($("#selectedUser").html() == "") {
        $("#divSelectedUser").hide();
    }
    else {
        $("#divSelectedUser").show();
    }

}
function updateUsers(userName) {

    // console.log('List of users : ' + userList);
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
                activeUserSpan.html('<p><i>No active users found.</i></p>');
            }
            else {
                activeUserSpan.html('<p class="text-muted">click on user to begin chat</p>');
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

function createUserNode(username) {

    return `
                <div class="chat_list active_chat" href="javascript:void(0)" onclick="setSelectedUser(`+'\''+username+'\''+`)">
                    <div class="chat_people">
                        <div class="chat_ib">
                            <h5>${username}<span class="chat_date">2021-01-14</span></h5>
                        </div>
                    </div>
                </div>
         `

}
