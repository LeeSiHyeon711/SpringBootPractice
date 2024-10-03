package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.repository.UserRepository;
import com.example.my_first_spring_boot.service.BoardService;
import com.example.my_first_spring_boot.service.CommentService;
import com.example.my_first_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;
}
