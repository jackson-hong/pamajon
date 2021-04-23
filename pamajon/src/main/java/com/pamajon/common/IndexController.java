package com.pamajon.common;

import com.pamajon.product.model.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.IntStream;

@Log4j2
@RestController
public class IndexController {

    @Value("${spring.application.name}")
    String appName;

    @Qualifier("boardServiceImpl")
    private final ProductService service;

    public IndexController(ProductService service){this.service = service;}

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv){
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

        log.info("CATERESULT: "+cateResult);

        mv.setViewName("home");
        mv.addObject("cateResult",cateResult);
        mv.addObject("resultList", resultList);
        mv.addObject("appName",appName);
        return mv;
    }
}
