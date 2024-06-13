package com.ecommerce.controller;


import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Admin")
public class AdminController {

    private ProductService productService;
    private ModelMapper modelMapper;

    private UserService userService;

    @Autowired
    public AdminController(ProductService productService,ModelMapper modelMapper,UserService userService){
        this.productService=productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

//    @GetMapping("/testAdmin")
//    public ResponseEntity<String> testAdmin(){
//        return new ResponseEntity<>("Admin test done ", HttpStatus.OK);
//    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(this.productService.getAllProduct().stream().map(product->modelMapper.map(product,ProductDto.class)).toList(),HttpStatus.OK);
    }

    @GetMapping("/getOneProduct/{id}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable Long id){
        return new ResponseEntity<>(this.productService.getOneProduct(id),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(modelMapper.map(this.userService.getOneUser(userId),UserDto.class),HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUser().stream().map(user -> modelMapper.map(user,UserDto.class)).toList(),HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user,@PathVariable Long id){
        return new ResponseEntity<>(modelMapper.map(this.userService.updateUser(user,id),UserDto.class),HttpStatus.OK);
    }


}
