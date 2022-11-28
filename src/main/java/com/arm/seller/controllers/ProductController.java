package com.arm.seller.controllers;

import com.arm.seller.entities.Product;
import com.arm.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/catalog")
    public String getCatalog(Model model){
        model.addAttribute("allProducts",productService
                .findAllProduct()
                .stream()
                .map(
                    product -> productService.productToProductDetails(product)
                )
                .collect(Collectors.toList())
        );
        return "catalog";
    }
}
