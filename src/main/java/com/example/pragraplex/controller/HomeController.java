package com.example.pragraplex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET,value = "/")
    public String index(Model model){
        model.addAttribute("className","Pushpinder Singh");
        return "index";
    }

}
