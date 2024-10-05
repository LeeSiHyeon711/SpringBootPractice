package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.my_first_spring_boot.entity.CommentEntity;
import com.example.my_first_spring_boot.repository.CommentRepository;
import com.example.my_first_spring_boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardService boardService;
    //댓글등록 컨트롤러
    @PostMapping("/addComment")
    public String addComment(@RequestParam("boardId") Long boardId, @ModelAttribute CommentEntity commentEntity, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        commentEntity.setAuthor(userName);
        BoardEntity boardEntity = boardService.findById(boardId);
        commentEntity.setBoardEntity(boardEntity);
        commentService.saveComment(commentEntity);
        return "redirect:/viewPost/"+commentEntity.getBoardEntity().getId();
    }
}
