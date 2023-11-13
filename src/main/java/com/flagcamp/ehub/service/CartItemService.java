package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.CartItemNotFoundException;
import com.flagcamp.ehub.exception.ItemLowInStockException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.*;
import com.flagcamp.ehub.repository.CartItemRepository;
import com.flagcamp.ehub.repository.HistoryRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;

    private final HistoryRepository historyRepository;

    public CartItemService(CartItemRepository cartItemRepository, ItemRepository itemRepository, HistoryRepository historyRepository) {
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
        this.historyRepository = historyRepository;
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

    @Transactional
    public void checkout(List<CheckedItem> items, User buyer) throws ItemNotFoundException, ItemLowInStockException {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for(CheckedItem cartItem:items){
            Item item = itemRepository.findItemById(cartItem.getId());
            if(item == null){
                throw new ItemNotFoundException("Item not found or has been deleted");
            }
            if(item.getStock() < cartItem.getCount()){
                throw new ItemLowInStockException("This item does not have requested stock");
            }
            result.append(item.toString() + cartItem.getCount() + ";");
            itemRepository.save(item.setStock(item.getStock() - cartItem.getCount()));
            cartItemRepository.deleteById(new CartItemKey(cartItem.getId(), buyer.getUsername()));
            historyRepository.save(new History()
                    .setOrder_id(UUID.randomUUID())
                    .setBuyer(buyer)
                    .setCheckOutTime(LocalDateTime.now().format(dateTimeFormatter2))
                    .setName(cartItem.getName())
                    .setOwner(cartItem.getOwner())
                    .setPrice(cartItem.price())
                    .setCount(cartItem.getCount()));
        }

    }
}
