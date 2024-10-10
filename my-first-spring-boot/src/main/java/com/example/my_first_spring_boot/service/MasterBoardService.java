package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.entity.MasterBoardEntity;
import com.example.my_first_spring_boot.repository.BoardRepository;
import com.example.my_first_spring_boot.repository.CommentRepository;
import com.example.my_first_spring_boot.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MasterBoardService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MasterRepository MasterRepository;
    @Autowired
    private final MasterRepository masterRepository;
    public MasterBoardService(com.example.my_first_spring_boot.repository.MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }
    //게시글 목록을 불러오는 서비스
    public List<BoardEntity> findAllBoards() { return MasterRepository.findAll(); }
    //게시글 총 갯수를 불러오는 서비스
    public long totalBoards() { return MasterRepository.count(); }
    //게시글 삭제 서비스
    public void deletePosts(List<Long> postIds) {
        masterRepository.deleteAllById(postIds);
    }
}