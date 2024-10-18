package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.OrderEntity;
import com.example.my_first_spring_boot.repository.OrderProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductsService {
    private final OrderProductsRepository orderProductsRepository;
    public OrderProductsService(OrderProductsRepository orderProductsRepository) {
        this.orderProductsRepository = orderProductsRepository;
    }
    //모든 주문 정보 가져오기 서비스
    public List<OrderEntity> getAllOrders() {
        return orderProductsRepository.findAll();
    }
    //주문 정보 입력 서비스
    public void alterOrders(OrderEntity orderEntity) {
        orderProductsRepository.save(orderEntity);
    }
}
