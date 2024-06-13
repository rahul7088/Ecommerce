package com.ecommerce.repositary;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositary extends JpaRepository<Product,Long> {
    List<Product> findByCustomer(User user);
    Product findByCustomerAndId(User user,Long id);
}
