package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.ImageNotFoundException;
import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.ItemImage;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.ImageRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ImageService imageService;

    private final ItemRepository itemRepository;

    private final ImageRepository imageRepository;

    public ItemService(ImageService imageService, ItemRepository itemRepository, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void add(Item item, MultipartFile[] images) {
        List<ItemImage> itemImages = Arrays.stream(images)
                .filter(image -> !image.isEmpty())
                .parallel()
                .map(imageService::save)
                .map(mediaLink -> new ItemImage(mediaLink, item))
                .collect(Collectors.toList());
        item.setImages(itemImages);
        itemRepository.save(item);
    }

    public void delete(UUID itemID, String username) throws ItemNotFoundException, ImageNotFoundException {
        Item item = itemRepository.findByIdAndOwner(itemID, new User.Builder().setUsername(username).build());
        List<ItemImage> images = imageRepository.findByItem(new Item.Builder().setId(itemID).build());
        if(item == null){
            throw new ItemNotFoundException("Item does not exist");
        }
        if(images == null){
            throw new ImageNotFoundException("Images do not exist");
        }
        for(ItemImage image: images){
            imageService.delete(image);
        }
        itemRepository.deleteById(itemID);
    }

    public List<Item> searchExactName(String itemName){
        return itemRepository.findByName(itemName);
    }

    public void updateQuantity(UUID id, int increasedBy){
        Item item = itemRepository.findItemById(id);
        itemRepository.save(item.setStock(item.getStock() + increasedBy));
    }
}
