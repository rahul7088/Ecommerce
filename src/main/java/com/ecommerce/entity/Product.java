package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String image;
    private int quantity;
    private int price;
    private String weight;
    private String description;
    private String createBy;
    private Date createDate;
    private String updtBy;
    private Date updtDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}
