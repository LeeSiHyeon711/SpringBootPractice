package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.OrderEntity;
import com.example.my_first_spring_boot.service.OrderProductsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController {
    @Autowired
    private final OrderProductsService orderProductsService;
    public OrderController(OrderProductsService orderProductsService) {
        this.orderProductsService = orderProductsService;
    }
    //주문입력 컨트롤러
}
