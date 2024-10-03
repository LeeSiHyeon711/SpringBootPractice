package com.example.my_first_spring_boot.repository;

import com.example.my_first_spring_boot.entity.UseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UseEntity, Integer> {
    //JpaRepository가 기본적으로 findAll, save, deleteById 등의 메서드를 제공함
}
