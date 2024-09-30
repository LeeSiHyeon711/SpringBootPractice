package com.example.my_first_spring_boot.controller;

import org.springframework.ui.Model;
import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardEntity> boardEntities = boardService.findAllBoards();
        model.addAttribute("boardEntities", boardEntities);
        return "boardList";
    }
    @GetMapping("/board/{id}")
    public String viewBoard(@PathVariable long id, Model model) {
        BoardEntity boardEntity = boardService.findBoardById(id);
        boardService.increaseViews(boardEntity);//조회수 증가
        model.addAttribute("boardEntity", boardEntity);
        return "viewPost";
    }
}
