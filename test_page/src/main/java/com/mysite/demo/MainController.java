package com.mysite.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        return "?•ˆ?…•?•˜?„¸?š” sbb?— ?˜¤?‹ ê²ƒì„ ?™˜?˜?•©?‹ˆ?‹¤.";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}