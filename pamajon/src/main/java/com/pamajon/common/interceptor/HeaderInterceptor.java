package com.pamajon.common.interceptor;

import com.pamajon.member.model.vo.Member;
import com.pamajon.product.model.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.IntStream;

@Log4j2
@Component("HeaderInterceptor")
public class HeaderInterceptor implements HandlerInterceptor {

    @Qualifier("boardServiceImpl")
    private final ProductService service;

    public HeaderInterceptor(ProductService service){
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession sess = request.getSession();

        List<List<HashMap>> cateList = (List<List<HashMap>>)sess.getAttribute("cateResult");

        if(cateList == null) {
            List<HashMap> resultList = service.homeBoard();

            List<HashMap> bigCateList = service.bigCateList();

            List<HashMap> smallCateList = service.smallCateList();

            List<List<HashMap>> cateResult = new ArrayList<>();
            IntStream.range(0, bigCateList.size()).forEach(index -> {
                List<HashMap> temp = new ArrayList<HashMap>();
                temp.add(bigCateList.get(index));
                cateResult.add(index, temp);
            });

            smallCateList.stream().forEach(smallCate -> {
                Integer bigCateId = (Integer)smallCate.get("PRO_CAT_WRAPPER_ID");
                cateResult.stream().forEach(ele -> {
                    Integer temp = (Integer) ele.get(0).get("PRO_CAT_WRAPPER_ID");
                    if(temp.equals(bigCateId)){
                        ele.add(smallCate);
                    }
                });
            });

            sess.setAttribute("cateResult", cateResult);
        }

        return true;
    }
}
