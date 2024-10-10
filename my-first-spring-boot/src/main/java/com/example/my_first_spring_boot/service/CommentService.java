package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.entity.CommentEntity;
import com.example.my_first_spring_boot.repository.BoardRepository;
import com.example.my_first_spring_boot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    //게시글 번호에 맞는 댓글 목록을 불러오는 서비스
    public List<CommentEntity> findByBoardId(Long id){
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다"));
        return commentRepository.findByBoardEntity(boardEntity);
    }
    //댓글 등록 서비스
    public void saveComment(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
    }
    //댓글 목록 불러오기 서비스
    public List<CommentEntity> findAllComment() {
        return commentRepository.findAll();
    }
    //댓글 삭제 서비스
    public void deleteComments(List<Long> commentId) {
        commentRepository.deleteAllById(commentId);
    }
}
