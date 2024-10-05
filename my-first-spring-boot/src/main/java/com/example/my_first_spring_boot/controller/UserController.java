package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //회원가입 폼을 보여주는 컨트롤러
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UseEntity());
        return "register";
    }
    //회원가입 정보를 받아서 처리하는 컨트롤러
    @PostMapping("/register")
    public String register(@ModelAttribute UseEntity user) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
