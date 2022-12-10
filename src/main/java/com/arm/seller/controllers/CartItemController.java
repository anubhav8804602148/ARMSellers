package com.arm.seller.controllers;

import com.arm.seller.entities.CartItem;
import com.arm.seller.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class CartItemController {

    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping("/cartItem/{id}")
    public List<CartItem> findCartItemByAnyId(@PathVariable("id") String itemId){
        if(itemId==null||!Pattern.matches("[\\da-zA-Z]{1,10}",itemId)){
            return new ArrayList<>();
        }
        long id = Long.parseLong(itemId);
        CartItem cartItemBySelfId = cartItemRepository.findCartItemById(id);
        List<CartItem> cartItemByBuyerId = cartItemRepository.findCartItemByBuyerId(id);
        List<CartItem> cartItemBySellerId = cartItemRepository.findCartItemBySellerId(id);
        List<CartItem> resultSet = new ArrayList<>();
        if(cartItemBySelfId!=null) resultSet.add(cartItemBySelfId);
        if(cartItemByBuyerId!=null) resultSet.addAll(cartItemByBuyerId);
        if(cartItemBySellerId!=null) resultSet.addAll(cartItemBySellerId);
        return resultSet;
    }
}
