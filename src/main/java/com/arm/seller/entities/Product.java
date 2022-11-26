package com.arm.seller.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private long sellerId;
    private Date mfgDate;
    private Date expiryDate;
    private int quantity;
    private long batchNumber;
    private double price;

    public Product(String name, String description, long sellerId, Date mfgDate,
                   Date expiryDate, int quantity, long batchNumber, double price) {
        this.name = name;
        this.description = description;
        this.sellerId = sellerId;
        this.mfgDate = mfgDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
        this.price = price;
    }

    public Product(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id && sellerId == product.sellerId && quantity == product.quantity &&
                batchNumber == product.batchNumber && Double.compare(product.price, price) == 0 &&
                name.equals(product.name) && description.equals(product.description) &&
                mfgDate.equals(product.mfgDate) && Objects.equals(expiryDate, product.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, sellerId, mfgDate,
                expiryDate, quantity, batchNumber, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", mfgDate=" + mfgDate +
                ", expiryDate=" + expiryDate +
                ", quantity=" + quantity +
                ", batchNumber=" + batchNumber +
                ", price=" + price +
                '}';
    }
}
