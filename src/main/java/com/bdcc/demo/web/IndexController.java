package com.bdcc.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        return "redirect:http://localhost:8080/CinemaManagement/";
    }


}
