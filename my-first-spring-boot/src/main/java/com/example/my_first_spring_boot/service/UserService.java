package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //사용자 이름을 찾는 메서드
    public UseEntity findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
    }
    //id로 사용자 정보를 찾아오는 메서드
    public UseEntity findNameById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
    //사용자 정보 저장 서비스
    public void saveUser(UseEntity user) {
        userRepository.save(user);
    }
    //모든 유저 정보 서비스
    public List<UseEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
