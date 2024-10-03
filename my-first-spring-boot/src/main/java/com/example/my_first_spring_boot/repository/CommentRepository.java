package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBoardEntity(BoardEntity boardEntity);
    //JpaRepository가 기본적으로 findAll, save, deleteById 등의 메서드를 제공함
}
