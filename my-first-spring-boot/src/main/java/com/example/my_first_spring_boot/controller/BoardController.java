package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.repository.BoardRepository;
import org.springframework.ui.Model;
import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;//서비스 가져오기 및 자동 주입
    @Autowired
    private BoardRepository boardRepository;//레파지토리 가져오기 및 자동 주입
    //게시판 글 목록 컨트롤러
    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardEntity> boardEntities = boardService.findAllBoards();
        long totalCount = boardService.totalBoards();
        model.addAttribute("boardEntities", boardEntities);
        model.addAttribute("totalCount", totalCount);
        return "boardList";
    }
    //게시글 상세보기 컨트롤러
    @GetMapping("/viewPost/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        BoardEntity boardEntity = boardService.findBoardById(id);
        boardService.increaseViews(boardEntity);//조회수 증가
        model.addAttribute("boardEntity", boardEntity);
        return "viewPost";
    }
    @GetMapping("/addBoardForm")
    //게시글 작성 폼 보여주기 컨트롤러
    public String showAddBoardForm(Model model) {
        model.addAttribute("boardEntity", new BoardEntity());
        return "addBoardForm";
    }
    //게시글 등록 정보 보내기 컨트롤러
    @PostMapping("/addBoard")
    public String addBoard(@ModelAttribute BoardEntity boardEntity) {
        boardService.saveBoard(boardEntity);
        return "redirect:/boardList";
    }
    //좋아요 버튼 클릭 정보 보내기 컨트롤러
    @PostMapping("/like")
    public String likeBoard(@RequestParam long id) {
        boardService.increaseLikes(id);
        return "redirect:/boardList";
    }

}