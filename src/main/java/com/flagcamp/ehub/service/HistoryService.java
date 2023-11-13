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
    public List<History> history(User buyer) throws CartEmptyException, ItemNotFoundException, ItemLowInStockException {
        return historyRepository.findHistoryByBuyer(buyer);
    }
}
