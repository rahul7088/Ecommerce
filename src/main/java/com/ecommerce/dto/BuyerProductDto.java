package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerProductDto {
    private Long id;
    private String name;
    private String image;
    private int quantity;
    private int price;
    private String weight;
    private String description;
}
