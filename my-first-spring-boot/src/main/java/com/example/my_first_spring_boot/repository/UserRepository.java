package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.UseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UseEntity, Long> {
    //JpaRepository가 기본적으로 findAll, save, deleteById 등의 메서드를 제공함
    Optional<UseEntity> findById(String id);
}
