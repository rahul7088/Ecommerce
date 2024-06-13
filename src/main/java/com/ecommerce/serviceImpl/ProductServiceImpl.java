package com.ecommerce.serviceImpl;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.repositary.ProductRepositary;
import com.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepositary productRepositary;
    private ModelMapper modelMapper;


    public ProductServiceImpl(ProductRepositary productRepositary,ModelMapper modelMapper){
        this.productRepositary = productRepositary;
        this.modelMapper = modelMapper;
    }

    public ProductDto addProduct(Product product, User user) {
        product.setCustomer(user);
        product.setCreateBy(user.getName());
        product.setCreateDate(new Date());
        product.setUpdtDate(new Date());
        product.setUpdtBy(user.getName());
        Product saveProduct = this.productRepositary.save(product);
        ProductDto productDto = modelMapper.map(saveProduct,ProductDto.class);
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return this.productRepositary.findAll().stream().map(product -> modelMapper.map(product,ProductDto.class)).toList();
    }

    @Override
    public ProductDto getOneProduct(Long id) {
        Product product =this.productRepositary.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found with id  "+id));
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByUser(User user) {
        return this.productRepositary.findByCustomer(user).stream().map(product -> modelMapper.map(product,ProductDto.class)).toList();
    }

    @Override
    public ProductDto getProductByUserAndId(User user, Long id) {
        Product product =this.productRepositary.findByCustomerAndId(user,id);
        return modelMapper.map(product,ProductDto.class);
    }
    @Override
    public ProductDto updateProduct(Product product,User user,Long id) {
        Product oldProduct =null;
        if(user.getRoles().equals("ADMIN"))
        {
            oldProduct =this.productRepositary.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found with id "+id));
        }else{
            oldProduct =this.productRepositary.findByCustomerAndId(user,id);
        }

        product.setId(oldProduct.getId());
        product.setUpdtBy(user.getName());
        product.setUpdtDate(new Date());
        product.setCustomer(oldProduct.getCustomer());
        ProductDto updatedProduct =  modelMapper.map(this.productRepositary.save(product),ProductDto.class);
        updatedProduct.setCustomerId(oldProduct.getCustomer().getUsedId());
        return updatedProduct;
    }

    @Override
    public String deleteProduct(User user, Long id) {
        System.out.println("User details "+user);
        Product product = null;
        try {
            if(user.getRoles().equals("ADMIN")){
                product = this.productRepositary.findById(id).get();
                this.productRepositary.delete(product);
            }else{
                product = modelMapper.map(this.getProductByUserAndId(user,id),Product.class);
                this.productRepositary.delete(product);
            }

            return "product is delete successfully.......";
        }catch (Exception e){
            e.printStackTrace();
            return "Product is not delete due to some error "+e.getMessage();
        }
    }


}
