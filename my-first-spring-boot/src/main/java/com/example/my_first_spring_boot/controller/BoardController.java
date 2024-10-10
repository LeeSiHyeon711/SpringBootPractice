package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.CommentEntity;
import com.example.my_first_spring_boot.entity.MasterBoardEntity;
import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.repository.BoardRepository;
import com.example.my_first_spring_boot.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.my_first_spring_boot.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private final BoardService boardService;//서비스 가져오기 및 자동 주입
    @Autowired
    private BoardRepository boardRepository;//레파지토리 가져오기 및 자동 주입
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private final MasterBoardService masterBoardService;
    @Autowired
    private AuthService authService;

    public BoardController(BoardService boardService, MasterBoardService masterBoardService) {
        this.boardService = boardService;
        this.masterBoardService = masterBoardService;
    }

    //게시판 글 목록 컨트롤러
    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardEntity> boardEntities = boardService.getAllBoards();
        long totalCount = boardService.totalBoards();
        model.addAttribute("boardEntities", boardEntities);
        model.addAttribute("totalCount", totalCount);
        return "boardList";
    }
    //게시글 상세보기 컨트롤러
    @GetMapping("/viewPost/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        BoardEntity boardEntity = boardService.findBoardById(id);
        boardService.increaseViews(id);//유저보드 조회수 증가
        model.addAttribute("boardEntity", boardEntity);
        //해당게시물의 댓글보기
        List<CommentEntity> commentEntities = commentService.findByBoardId(id);
        model.addAttribute("commentEntities", commentEntities);
        return "viewPost";
    }
    //게시글 작성 폼 보여주기 컨트롤러(로그인 시 세션에 저장된 이름을 불러옴)
    @GetMapping("/addBoardForm")
    public String showAddBoardForm(Model model, HttpSession httpSession) {
        BoardEntity boardEntity = new BoardEntity();
        String loggedInUser = (String) httpSession.getAttribute("userName");
        boardEntity.setAuthor(loggedInUser);
        model.addAttribute("boardEntity", new BoardEntity());
        return "addBoardForm";
    }
    //게시글 등록 정보 보내기 컨트롤러(로그인 시 세션에 저장된 이름, id를 전송함)
    //추가 : 관리자 테이블에도 게시글 정보 동시에 보
    @PostMapping("/addBoard")
    public String addBoard(@ModelAttribute BoardEntity boardEntity, HttpSession httpSession) {
        String loggedInUser = (String) httpSession.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }
        UseEntity user = userService.findById(loggedInUser);
        boardEntity.setUser(user);
        boardEntity.setAuthor(user.getName());
        boardService.saveBoard(boardEntity);
        // MasterBoardEntity 생성
        MasterBoardEntity masterBoardEntity = new MasterBoardEntity();
        masterBoardEntity.setId(boardEntity.getId());
        masterBoardEntity.setTitle(boardEntity.getTitle());
        masterBoardEntity.setAuthor(boardEntity.getAuthor());
        masterBoardEntity.setContent(boardEntity.getContent());
        masterBoardEntity.setCreateDate(boardEntity.getCreateDate());
        masterBoardEntity.setUser(boardEntity.getUser());
        // 두 개의 엔티티를 함께 저장하도록 서비스 호출
        boardService.saveBoardWithMaster(boardEntity, masterBoardEntity);
        return "redirect:/boardList";
    }
    //좋아요 버튼 클릭 정보 보내기 컨트롤러
    @PostMapping("/like")
    public String likeBoard(@RequestParam long id) {
        boardService.increaseLikes(id);
        return "redirect:/boardList";
    }
    //게시글 삭제 컨트롤러
    //추가: 세션기록과 게시물 작성자 id비교. 일치해야 삭제가능
    //추가 : 관리자는 모든 조건 무시. 삭제 가능
    @PostMapping("/deletePost")
    public String deleteBoard(@RequestParam long id, HttpSession session) {
        BoardEntity boardEntity = boardService.findById(id);
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        String userRole = (String) session.getAttribute("userRole");
        if (boardEntity.getUser().getId().equals(loggedInUser) || "MASTER".equals(userRole)){
            boardService.deleteBoardById(id);
            return "redirect:/boardList";
        } else {
            return "error/403";
        }
    }
    //게시글 수정 페이지로 이동(GET 요청)
    //추가: 세션기록과 게시물 작성자 id비교. 일치해야 수정가능
    //추가 : 관리자는 모든 조건 무시. 수정 가능
    @GetMapping("/editBoard/{id}")
    public String showEditBoardForm(@PathVariable("id") long id, Model model, HttpSession session) {
        BoardEntity boardEntity = boardService.findById(id);
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        String userRole = (String) session.getAttribute("userRole");
        if (boardEntity.getUser().getId().equals(loggedInUser) || "MASTER".equals(userRole)){
            model.addAttribute("boardEntity", boardEntity);
            return "editBoardForm";
        }else {
            return "error/403";
        }
    }
    //게시글 수정하기 컨트롤러(POST 요청)
    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") long id, @ModelAttribute("boardEntity") BoardEntity boardEntity) {
        boardService.editBoard(id, boardEntity);
        return "redirect:/boardList";
    }
}