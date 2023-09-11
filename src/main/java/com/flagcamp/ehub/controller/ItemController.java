package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.ImageNotFoundException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

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
                .setName(title)
                .setDescription(description)
                .setPrice(price)
                .setStock(inventory)
                .setOwner(new User.Builder().setUsername(principal.getName()).build())
                .build();
        itemService.add(item, images);
    }

    @DeleteMapping("/sell/{itemId}")
    public void deleteItem(@PathVariable UUID itemId, Principal principal) throws ItemNotFoundException, ImageNotFoundException {
        itemService.delete(itemId, principal.getName());
    }

    @GetMapping("/search")
    public List<Item> searchItem(@RequestParam("Title") String title){
        return itemService.searchExactName(title);
    }
}
