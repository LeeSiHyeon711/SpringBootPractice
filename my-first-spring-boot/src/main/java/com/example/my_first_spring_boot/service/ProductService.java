package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.ProductEntity;
import com.example.my_first_spring_boot.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //모든 상품 정보 가져오기 서비스
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
    //상품의 상세페이지 서비스
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. ID: " + id));
    }
}
