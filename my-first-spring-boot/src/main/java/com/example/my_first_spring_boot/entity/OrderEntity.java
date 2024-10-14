package com.example.my_first_spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order_products")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity productEntity;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "total_price")
    private Long totalPrice;
    private Long quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_num", nullable = false)
    private UseEntity user;
    @Column(name = "order_status")
    private String orderStatus = "주문 확인";
}
