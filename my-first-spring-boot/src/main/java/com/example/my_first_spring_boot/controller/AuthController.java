package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.service.AuthService;
import com.example.my_first_spring_boot.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }
    //로그인 페이지 이동 컨트롤러
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    //로그인 정보 확인 컨트롤러
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("id") String id, @RequestParam("pass") String pass) {
        boolean isAuthenticated = authService.authenticate(id, pass);
        if (isAuthenticated) {
            UseEntity user = userService.findById(id);
            session.setAttribute("userName", user.getName());  // 이름을 세션에 저장
            session.setAttribute("loggedInUser", user.getId());    // 로그인된 사용자 ID 저장
            session.setAttribute("userRole", user.getRole()); //역할을 세션에 저장(MASTER,USER)
            if ("MASTER".equals(user.getRole())) { //관리자로 로그인할경우 관리자페이지로 바로 넘어감
                return "redirect:/masterPage";
            }else {
                return "redirect:/products";
            }
        } else {
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/products";
    }
}
