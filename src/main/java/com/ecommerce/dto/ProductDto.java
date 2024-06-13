package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
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
    private Long customerId;
}
