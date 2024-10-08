package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.MasterBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBoardRepository extends JpaRepository<MasterBoardEntity, Long> {
}
