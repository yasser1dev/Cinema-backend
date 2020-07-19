package com.bdcc.demo.security;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {



    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return "redirect:/login";
    }
}
