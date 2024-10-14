package com.example.my_first_spring_boot.controller;

import com.example.my_first_spring_boot.entity.ProductEntity;
import com.example.my_first_spring_boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //상품리스트 출력 컨트롤러
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList";
    }
    //상품 상세보기 출력 컨트롤러
    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        ProductEntity product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetail";
    }
}
