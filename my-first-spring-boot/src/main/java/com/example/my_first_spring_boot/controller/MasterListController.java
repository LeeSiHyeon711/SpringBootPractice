package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.service.BoardService;
import com.example.my_first_spring_boot.service.MasterBoardService;
import com.example.my_first_spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MasterListController {
    private final MasterBoardService MasterBoardService;
    private final MasterBoardService masterBoardService;
    private final BoardService boardService;
    private final UserService userService;

    public MasterListController(MasterBoardService MasterBoardService, MasterBoardService masterBoardService, BoardService boardService, UserService userService) {
        this.MasterBoardService = MasterBoardService;
        this.masterBoardService = masterBoardService;
        this.boardService = boardService;
        this.userService = userService;
    }
    //관리자용 게시판 글 목록 컨트롤러
    @GetMapping("/masterPage")
    public String boardList(Model model) {
        //게시판 목록 가져오기
        List<BoardEntity> boardEntities = MasterBoardService.findAllBoards();
        long totalCount = MasterBoardService.totalBoards();
        model.addAttribute("boardEntities", boardEntities);
        model.addAttribute("totalCount", totalCount);
        //유저 목록 가져오기
        List<UseEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        //유저 총인원 가져오기
        long userCount = userService.totalUsers();
        model.addAttribute("userCount", userCount);
        return "masterPage";


    }
    // 관리자용 게시글 삭제 컨트롤러
    @PostMapping("/deletePosts")
    public String deletePosts(@RequestParam(value = "boardIds", required = false) List<Long> postIds) {
        if (postIds != null && !postIds.isEmpty()) {
            masterBoardService.deletePosts(postIds);
            return "redirect:/masterPage?section=posts";
        } else {
            // 아무 게시글도 선택되지 않았을 때 처리
            return "redirect:/masterPage?section=posts&error=noSelection";
        }
    }


}
