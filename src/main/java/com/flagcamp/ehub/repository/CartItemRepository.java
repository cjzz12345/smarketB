package com.flagcamp.ehub.repository;

import com.flagcamp.ehub.model.CartItem;
import com.flagcamp.ehub.model.CartItemKey;
import com.flagcamp.ehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

    CartItem findByCartItemKey(CartItemKey key);

    List<CartItem> findByBuyer(User buyer);

    void deleteByBuyer(User buyer);
}
