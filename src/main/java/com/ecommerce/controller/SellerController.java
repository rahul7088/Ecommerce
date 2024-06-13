package com.ecommerce.controller;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.security.JwtHelper;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/Seller")
@NoArgsConstructor
public class SellerController {

   private ProductService productService;
    private JwtHelper helper;
    private UserService userService;
   @Autowired
   public  SellerController(ProductService productService,JwtHelper helper,UserService userService,HttpServletRequest request){
        this.productService = productService;
        this.helper = helper;
        this.userService = userService;
    }
    public  SellerController(JwtHelper helper,UserService userService){
       this.helper = helper;
       this.userService = userService;
    }


//    @GetMapping("/testSeller")
//    public ResponseEntity<String> testSeller(){
//        System.out.println("Test Seller Controller ...........................");
//       return new ResponseEntity<>("test Seller",HttpStatus.OK);
//    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product, HttpServletRequest request){
        User user = getUserFromToken(request.getHeader("Authorization"));
        return new ResponseEntity<>(this.productService.addProduct(product,user), HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(HttpServletRequest request){
        User user = getUserFromToken(request.getHeader("Authorization"));
        return new ResponseEntity<>(this.productService.getProductByUser(user),HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id")Long id,HttpServletRequest request){
        User user = getUserFromToken(request.getHeader("Authorization"));
       return new ResponseEntity<>(this.productService.getProductByUserAndId(user,id),HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product,@PathVariable("id") Long id,HttpServletRequest request){
        User user = getUserFromToken(request.getHeader("Authorization"));
       return new ResponseEntity<>(this.productService.updateProduct(product,user,id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id,HttpServletRequest request){
        User user = getUserFromToken(request.getHeader("Authorization"));
        return new ResponseEntity<>(this.productService.deleteProduct(user,id),HttpStatus.OK);
    }
    public User getUserFromToken(String headerValue){
        String  token = headerValue.substring(7);
        String username = helper.getUsernameFromToken(token);
        Optional<User> user  = Optional.ofNullable((User) userService.loadUserByUsername(username));
        return user.orElseThrow(()->new ResourceNotFoundException("Header is null.."));
    }
}
