package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.CartItemNotFoundException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.CartItem;
import com.flagcamp.ehub.model.CartItemKey;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.CartItemRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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
            cartItemRepository.save(new CartItem(new CartItemKey(id, username), new User.Builder().setUsername(username).build(), quantity));
        }else{
            cartItem.setCount(quantity + cartItem.getCount());
            cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> get(String username){
        return cartItemRepository.findByBuyer(new User.Builder().setUsername(username).build());
    }

    @Transactional
    public void delete(UUID id, String username) throws CartItemNotFoundException {
        CartItemKey key = new CartItemKey(id, username);
        CartItem cartItem = cartItemRepository.findByCartItemKey(key);
        if(cartItem == null){
            throw new CartItemNotFoundException("CartItem does not exist");
        }else{
            cartItemRepository.deleteById(key);
        }
    }
}