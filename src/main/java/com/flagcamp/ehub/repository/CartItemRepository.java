package com.flagcamp.ehub.repository;

import com.flagcamp.ehub.model.CartItem;
import com.flagcamp.ehub.model.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

    CartItem findByCartItemKey(CartItemKey key);
}
