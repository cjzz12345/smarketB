package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.ItemImage;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.ImageRepository;
import com.flagcamp.ehub.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ImageService imageService;

    private final ItemRepository itemRepository;

    private final ImageRepository imageRepository;

    @Value("${s3.url}")
    private String s3URL;

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

    @Transactional
    public void delete(UUID itemID, String username) throws ItemNotFoundException {
        Item item = itemRepository.findByIdAndOwner(itemID, new User.Builder().setUsername(username).build());
        if(item == null){
            throw new ItemNotFoundException("Item does not exist");
        }
        itemRepository.deleteById(itemID);
    }

    public List<Item> searchExactName(String itemName){
        return itemRepository.findByName(itemName)
                .stream()
                .map(item -> item.setImages(item.getImages()
                        .stream()
                        .map(image -> image.setUrl(s3URL+image.getUrl()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public Item searchId(UUID itemID) throws ItemNotFoundException {
        Item item = itemRepository.findItemById(itemID);
        if(item == null){
            throw new ItemNotFoundException("Item with uuid" + itemID + "does not exist");
        }
        item.setImages(item.getImages()
                .stream()
                .map(image -> {
                    image.setUrl(s3URL + image.getUrl());
                    return image;
                })
                .collect(Collectors.toList()));
        return item;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll()
                .stream()
                .map(item -> item.setImages(item.getImages()
                        .stream()
                        .map(image -> image.setUrl(s3URL+image.getUrl()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
