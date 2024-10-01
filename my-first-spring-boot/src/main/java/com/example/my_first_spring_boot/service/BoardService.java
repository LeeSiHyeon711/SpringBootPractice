package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    //게시글 목록을 불러오는 서비스
    public List<BoardEntity> findAllBoards() {
        return boardRepository.findAll();
    }
    //게시글 총 갯수를 불러오는 서비스
    public long totalBoards() { return boardRepository.count(); }
    //id에 해당하는 게시글을 불러오는 서비스
    public BoardEntity findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Board Id:"+id));
    }
    //게시글 조회시 조회수 증가 서비스
    public void increaseViews(BoardEntity boardEntity) {
        boardEntity.setViews(boardEntity.getViews() + 1);
        boardRepository.save(boardEntity);
    }
    //게시글 등록 서비스
    public void saveBoard(BoardEntity boardEntity) {
        boardRepository.save(boardEntity);
    }
}
