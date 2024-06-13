package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

import java.util.List;

public interface ProductService {
    //seller

    //Add Product by Seller and admin both
    ProductDto addProduct(Product product, User user);

    //Get All Product by Seller
    List<ProductDto> getProductByUser(User user);

    //Get One Product By Seller
    ProductDto getProductByUserAndId(User user,Long id);

    //update product By Seller
    ProductDto updateProduct(Product product,User user,Long id);

    //Delete Product By Seller
    String deleteProduct(User user,Long id);

    //Admin
    //get All Product by Admin or buyer
    List<ProductDto> getAllProduct();

    //get All Product by Admin or buyer
    ProductDto getOneProduct(Long id);





}
