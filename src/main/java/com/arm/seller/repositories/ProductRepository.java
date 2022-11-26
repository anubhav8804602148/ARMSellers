package com.arm.seller.repositories;

import com.arm.seller.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT P FROM Product P WHERE P.name=?1")
    public List<Product> findProductByProductName(String name);
    @Query("SELECT P FROM Product P WHERE P.id=?1")
    public Product findProductById(long id);
}
