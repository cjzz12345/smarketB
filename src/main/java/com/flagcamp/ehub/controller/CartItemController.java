package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.CartItemNotFoundException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/cart")
    public void addItemToCart(@RequestParam("Item_id") UUID id,
                              @RequestParam("Quantity") int count,
                              Principal principal) throws ItemNotFoundException {
//        cartItemService.add(id, principal.getName(), count);
        cartItemService.add(id, "asdf", count);
    }

    @DeleteMapping("/cart/{item_id}")
    public void deleteItemFromCart(@PathVariable UUID item_id, Principal principal) throws CartItemNotFoundException {
//        cartItemService.delete(item_id, principal.getName());
        cartItemService.delete(item_id, "asdf");
    }
}
