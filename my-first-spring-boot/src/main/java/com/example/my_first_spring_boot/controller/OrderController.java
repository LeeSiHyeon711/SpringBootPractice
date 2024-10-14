package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.service.OrderProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController {
    @Autowired
    private final OrderProductsService orderProductsService;
    public OrderController(OrderProductsService orderProductsService) {
        this.orderProductsService = orderProductsService;
    }
}
