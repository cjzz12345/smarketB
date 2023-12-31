package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.ImageNotFoundException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
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

    @GetMapping("/item")
    public Item getItemById(@RequestParam UUID id) throws ItemNotFoundException {
        return itemService.searchId(id);
    }

    @GetMapping("/products")
    public List<Item> getItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/list")
    public List<Item> getItemByOwner(@RequestParam("username") String username){
        return itemService.getItemsByOwner(username);
    }

    @PostMapping("/list/products")
    public List<Item> getListProducts(@RequestBody List<UUID> ids) throws ItemNotFoundException{
        List<Item> items = new ArrayList<>();
        for (UUID id : ids) { System.out.println(items);

            items.add(itemService.searchId(id));
            System.out.println(items);
        }
        System.out.println(items);
        return items;
    }
}
