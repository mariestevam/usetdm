package br.com.usetdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class IndexController {
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/error")
    public String loginError(){
        return "login/login-error";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        //perform audit action
        return "/";
    }

    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        //perform audit action
        return "login";
    }

    @PostMapping("/error")
    public String error() {
        //perform audit action
        return "/login/login-error";
    }
}


