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
    public List<BoardEntity> findAllBoards() {
        return boardRepository.findAll();
    }
    public BoardEntity findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Board Id:"+id));
    }
    public void increaseViews(BoardEntity boardEntity) {
        boardEntity.setViews(boardEntity.getViews() + 1);
        boardRepository.save(boardEntity);
    }
}
