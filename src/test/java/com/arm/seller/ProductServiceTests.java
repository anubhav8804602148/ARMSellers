package com.arm.seller;

import com.arm.seller.service.ProductService;
import com.arm.seller.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTests {

    Product p1;
    Product p2;
    Product p3;
    Product p4;
    Product p5;
    Product p6;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void createSomeDummyProducts(){
        p1 = new Product("Product1","ProductDescr1",426982,new Date(628436895),
                new Date(843260),12,436324,23868);
        p2 = new Product("Product2","ProductDescr2",634843,new Date(632506465),
                new Date(843260),12,436324,23868);
        p3 = new Product("Product3","ProductDescr3",734993,new Date(235325473),
                new Date(843260),12,436324,23868);
        p4 = new Product("Product4","ProductDescr4",347683,new Date(329863265),
                new Date(843260),12,436324,23868);
        p5 = new Product("Product5","ProductDescr5",532989,new Date(342650344),
                new Date(843260),12,436324,23868);
        p6 = new Product("Product6","ProductDescr6",923708,new Date(239235378),
                new Date(843260),12,436324,23868);
    }

    @AfterEach
    public void cleanUp(){

    }

    @Test
    public void testProductCreation(){
        assertThat(productService.registerNewProduct(p1)).isEqualTo(p1);
        assertThat(productService.registerNewProduct(p2)).isEqualTo(p2);
        assertThat(productService.registerNewProduct(p3)).isEqualTo(p3);
        assertThat(productService.registerNewProduct(p4)).isEqualTo(p4);
        assertThat(productService.registerNewProduct(p5)).isEqualTo(p5);
        assertThat(productService.registerNewProduct(p6)).isEqualTo(p6);
    }
}
