package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    //JpaRepository가 기본적으로 findAll, save, deleteById 등의 메서드를 제공함
    List<BoardEntity> findAllByOrderByCreateDateDesc();
}
