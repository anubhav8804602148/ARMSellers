package com.arm.seller.repositories;

import com.arm.seller.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci where ci.id=?1")
    public List<CartItem> findCartItemByBuyerId(long id);
    @Query("select ci from CartItem ci where ci.sellerId=?1")
    public List<CartItem> findCartItemBySellerId(long id);
    @Query("select ci from CartItem ci where ci.buyerId=?1")
    public CartItem findCartItemById(long id);

}
