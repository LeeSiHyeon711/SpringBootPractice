package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.service.MasterBoardService;
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

    public MasterListController(MasterBoardService MasterBoardService, MasterBoardService masterBoardService) {
        this.MasterBoardService = MasterBoardService;
        this.masterBoardService = masterBoardService;
    }
    //관리자용 게시판 글 목록 컨트롤러
    @GetMapping("/masterPage")
    public String boardList(Model model) {
        List<BoardEntity> boardEntities = MasterBoardService.findAllBoards();
        long totalCount = MasterBoardService.totalBoards();
        model.addAttribute("boardEntities", boardEntities);
        model.addAttribute("totalCount", totalCount);
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
