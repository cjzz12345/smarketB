package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.CartItem;
import com.flagcamp.ehub.model.CartItemKey;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.repository.CartItemRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;

    public CartItemService(CartItemRepository cartItemRepository, ItemRepository itemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void add(UUID id, String username, int quantity) throws ItemNotFoundException {
        Item item = itemRepository.findItemById(id);
        if(item == null){
            throw new ItemNotFoundException("Item does not exist");
        }
        CartItem cartItem = cartItemRepository.findByCartItemKey(new CartItemKey(id, username));
        if(cartItem == null){
            cartItemRepository.save(new CartItem(new CartItemKey(id, username), item, quantity));
        }else{
            cartItem.setCount(quantity + cartItem.getCount());
            cartItemRepository.save(cartItem);
        }
    }
}
