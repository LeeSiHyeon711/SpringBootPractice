package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.MasterBoardEntity;
import com.example.my_first_spring_boot.entity.UseEntity;
import com.example.my_first_spring_boot.repository.MasterBoardRepository;
import com.example.my_first_spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final MasterBoardRepository masterBoardRepository;
    public AuthService(UserRepository userRepository, MasterBoardRepository masterBoardRepository) {
        this.userRepository = userRepository;
        this.masterBoardRepository = masterBoardRepository;
    }
    //사용자의 id,pass 가 일치하는 정보인지 확인
    public boolean authenticate(String id, String pass) {
        Optional<UseEntity> user = userRepository.findById(id);
        if(user.isPresent() && user.get().getPass().equals(pass)) {
            return true; // 인증 성공
        }else {
            return false; // 인증 실패
        }
    }
}
