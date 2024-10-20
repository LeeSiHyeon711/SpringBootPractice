package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.*;
import com.example.my_first_spring_boot.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MasterListController {
    private final MasterBoardService MasterBoardService;
    private final MasterBoardService masterBoardService;
    private final BoardService boardService;
    private final UserService userService;
    private final CommentService commentService;
    private final ProductService productService;
    private final OrderProductsService orderProductsService;

    public MasterListController(MasterBoardService MasterBoardService, MasterBoardService masterBoardService, BoardService boardService, UserService userService, CommentService commentService, ProductService productService, OrderProductsService orderProductsService) {
        this.MasterBoardService = MasterBoardService;
        this.masterBoardService = masterBoardService;
        this.boardService = boardService;
        this.userService = userService;
        this.commentService = commentService;
        this.productService = productService;
        this.orderProductsService = orderProductsService;
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
        //댓글 목록 가져오기
        List<CommentEntity> comments = commentService.findAllComment();
        model.addAttribute("comments", comments);
        //상품 리스트 가져오기
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        //주문 현황 가져오기
        List<OrderEntity> orders = orderProductsService.getAllOrders();
        model.addAttribute("orders", orders);
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
    // 관리자용 댓글 삭제 컨트롤러
    @PostMapping("/deleteComments")
    public String deleteComments(@RequestParam(value = "commentId", required = false) List<Long> commentId) {
        if (commentId != null && !commentId.isEmpty()) {
            commentService.deleteComments(commentId);
            return "redirect:/masterPage?section=comment";
        }else {
            return "redirect:/masterPage?section=comments&error=noSelection";
        }
    }
}
