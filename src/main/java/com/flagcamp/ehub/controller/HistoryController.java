package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.CartEmptyException;
import com.flagcamp.ehub.exception.ItemLowInStockException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.History;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.HistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/history")
    public List<History> history(Principal principal) throws CartEmptyException, ItemNotFoundException, ItemLowInStockException {
        return historyService.history(new User.Builder().setUsername(principal.getName()).build());
    }
}
