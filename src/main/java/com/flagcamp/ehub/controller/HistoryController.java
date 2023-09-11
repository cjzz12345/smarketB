package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.CartEmptyException;
import com.flagcamp.ehub.exception.ItemLowInStockException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.HistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/checkout")
    public void checkout(Principal principal) throws CartEmptyException, ItemNotFoundException, ItemLowInStockException {
        historyService.checkout(new User.Builder().setUsername(principal.getName()).build());
    }
}
