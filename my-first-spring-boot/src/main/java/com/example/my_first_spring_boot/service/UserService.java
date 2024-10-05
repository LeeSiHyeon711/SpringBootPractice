package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String findNameById(String id) {
        return userRepository.findById(id).map(UseEntity::getName).orElse("익명");
    }
    //사용자 정보 저장 서비스
    public void saveUser(UseEntity user) {
        userRepository.save(user);
    }
}
