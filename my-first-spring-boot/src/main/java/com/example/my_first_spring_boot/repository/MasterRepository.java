package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<BoardEntity, Long> {
}
