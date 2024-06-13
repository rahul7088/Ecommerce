package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private User customer;

    private int quantity;
    private double totalPrice;
    private Date orderDate;
    private Date createDt;
    private String createBy;
    private Date updateDt;
    private String updateBy;
    @PrePersist
    protected void onCreate() {
        this.createDt = new Date();
        this.orderDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDt = new Date();
    }

}
