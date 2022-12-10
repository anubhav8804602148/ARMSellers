package com.arm.seller.service;

import com.arm.seller.models.ProductDetails;
import com.arm.seller.repositories.ProductRepository;
import com.arm.seller.entities.Product;
import com.arm.seller.exceptions.ProductException.*;
import com.arm.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    UserRepository userRepo;

    public Product findProductById(long id) {
        return productRepo.findProductById(id);
    }

    public List<Product> findProductByProductName(String name){
        return productRepo.findProductByProductName(name);
    }

    public List<Product> findAllProduct(){
        return productRepo.findAll();
    }

    public List<Product> findProductByIdOrName(String name) {
        List<Product> resultSet = new ArrayList<>();
        resultSet = findProductByProductName(name);
        if(Pattern.matches("\\d{1,10}", name)){
            resultSet.add(findProductById(Long.parseLong(name)));
        }
        return resultSet;
    }

    public void deleteProductById(long id) throws NoSuchProductException{
        Product product = findProductById(id);
        if(product==null){
            throw new NoSuchProductException();
        }
        else{
            productRepo.delete(findProductById(id));
        }
    }

    public Product registerNewProduct(Product product){
        return productRepo.save(product);
    }

    public Product updateProduct(Product product) throws NullProductException{
        if(product==null){
            throw new NullProductException();
        }
        Product newProduct = productRepo.findProductById(product.getId());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setSellerId(product.getSellerId());
        newProduct.setMfgDate(product.getMfgDate());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setExpiryDate(product.getExpiryDate());
        newProduct.setBatchNumber(product.getBatchNumber());
        newProduct.setPrice(product.getPrice());
        return productRepo.save(newProduct);
    }

    public ProductDetails productToProductDetails(Product product){
        ProductDetails productDetails = new ProductDetails();
        if(!userRepo.findById(product.getSellerId()).isPresent()) return productDetails;
        productDetails.setName(product.getName());
        productDetails.setSellerName(userRepo.findById(product.getSellerId()).get().getFullName());
        productDetails.setBatchNumber(product.getBatchNumber());
        productDetails.setDescription(product.getDescription());
        productDetails.setExpiryDate(product.getExpiryDate());
        productDetails.setMfgDate(product.getMfgDate());
        productDetails.setPrice(product.getPrice());
        productDetails.setId(product.getId());
        productDetails.setSellerId(product.getSellerId());
        productDetails.setQuantity(product.getQuantity());
        return productDetails;
    }
}
