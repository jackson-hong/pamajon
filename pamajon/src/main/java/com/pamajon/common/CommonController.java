package com.pamajon.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Log4j2
@RestController
public class CommonController {

    @RequestMapping("/common/msg")
    ModelAndView msg(ModelAndView mv, @RequestParam Map map, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        log.info(map);
        mv.addObject("msg",map.get("msg"));
        mv.addObject("loc",map.get("loc"));
        mv.setViewName("/common/msg");
        return mv;
    }
}
