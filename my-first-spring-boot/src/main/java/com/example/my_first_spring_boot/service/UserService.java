package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.repository.BoardRepository;
import com.example.my_first_spring_boot.repository.CommentRepository;
import com.example.my_first_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
}
