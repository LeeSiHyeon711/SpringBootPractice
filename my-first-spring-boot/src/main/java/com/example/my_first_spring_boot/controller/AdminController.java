package com.example.my_first_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/masterPage")
    public String showMasterPage() {
        return "masterPage";
    }
}
