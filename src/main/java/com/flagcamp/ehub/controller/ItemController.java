package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/sell")
    public void addItem(@RequestParam("Title") String title,
                        @RequestParam("Description") String description,
                        @RequestParam("Price") float price,
                        @RequestParam("Inventory") int inventory,
                        @RequestParam("Images") MultipartFile[] images,
                        Principal principal){
        Item item = new Item.Builder()
                .setItem_name(title)
                .setItem_description(description)
                .setItem_price(price)
                .setItem_stock(inventory)
                .setOwner(new User.Builder().setUsername(principal.getName()).build())
                .build();
        itemService.add(item, images);
    }
}
