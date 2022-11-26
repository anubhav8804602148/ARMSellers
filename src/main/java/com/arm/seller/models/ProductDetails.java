package com.arm.seller.models;

import java.sql.Date;
import java.time.LocalDate;

public class ProductDetails {

    private long id;
    private String name;
    private String description;
    private long sellerId;
    private Date mfgDate;
    private Date expiryDate;
    private int quantity;
    private long batchNumber;
    private double price;

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

    public boolean isExpired() {
        return expiryDate.before(new Date(LocalDate.now().toEpochDay()));
    }

    public boolean isInStock() {
        return quantity>0;
    }
}
