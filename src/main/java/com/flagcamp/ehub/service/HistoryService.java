package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.CartEmptyException;
import com.flagcamp.ehub.exception.ItemLowInStockException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.CartItem;
import com.flagcamp.ehub.model.History;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.CartItemRepository;
import com.flagcamp.ehub.repository.HistoryRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class HistoryService {

    private final CartItemRepository cartItemRepository;

    private final HistoryRepository historyRepository;

    private final ItemRepository itemRepository;

    public HistoryService(CartItemRepository cartItemRepository, HistoryRepository historyRepository, ItemRepository itemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.historyRepository = historyRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void checkout(User buyer) throws CartEmptyException, ItemNotFoundException, ItemLowInStockException {
        List<CartItem> cartItemList = cartItemRepository.findByBuyer(buyer);
        if(cartItemList == null || cartItemList.size() == 0){
            throw new CartEmptyException("You don't have anything in your cart");
        }
        StringBuilder result = new StringBuilder();
        for(CartItem cartItem:cartItemList){
            Item item = itemRepository.findItemById(cartItem.getCartItemKey().getItem());
            if(item == null){
                throw new ItemNotFoundException("Item not found or has been deleted");
            }
            if(item.getStock() < cartItem.getCount()){
                throw new ItemLowInStockException("This item does not have requested stock");
            }
            result.append(item.toString() + cartItem.getCount() + ";");
            itemRepository.save(item.setStock(item.getStock() - cartItem.getCount()));
        }
        historyRepository.save(new History()
                .setOrder_id(UUID.randomUUID())
                .setBuyer(buyer)
                .setDetails(result.toString())
                .setCheckOutTime(LocalDateTime.now()));
        cartItemRepository.deleteByBuyer(buyer);
    }
}
