package com.arm.seller.controllers;

import com.arm.seller.entities.Product;
import com.arm.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public List<Product> getProductByid(@PathVariable String name){
        return productService.findProductByIdOrName(name);
    }
}
