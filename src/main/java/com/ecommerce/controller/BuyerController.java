package com.ecommerce.controller;

import com.ecommerce.dto.BuyerProductDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import com.ecommerce.security.JwtHelper;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Buyer")
public class BuyerController {

    private ProductService productService;
    private ModelMapper modelMapper;
    private OrderService orderService;
    private UserService userService;
    @Autowired
    public BuyerController(ProductService productService,ModelMapper modelMapper,OrderService orderService,UserService userService){
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.userService  = userService;
    }

//    @GetMapping("/testBuyer")
//    public ResponseEntity<String> getBuyer(){
//        return new ResponseEntity<>("this is a test buyer .......",HttpStatus.OK);
//    }

    @GetMapping("/searchAllProductByBuyer")
    public ResponseEntity<List<BuyerProductDto>> getAllProducts(){
        return new ResponseEntity<>(this.productService.getAllProduct().stream().map(product->modelMapper.map(product,BuyerProductDto.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/searchOneProductByBuyer/{id}")
    public ResponseEntity<BuyerProductDto> getOneProduct(@PathVariable Long id){
       return new ResponseEntity<>(modelMapper.map(this.productService.getOneProduct(id),BuyerProductDto.class),HttpStatus.OK);
    }

    @PostMapping("/purchaseOrder")
    public ResponseEntity<OrderDto> getOrder(@RequestBody Order order, HttpServletRequest request){
        User user = new SellerController(new JwtHelper(),userService).getUserFromToken(request.getHeader("Authorization"));
        return new ResponseEntity<>(this.orderService.addOrder(order,user),HttpStatus.OK);
    }

}
