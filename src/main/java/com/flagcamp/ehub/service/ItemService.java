package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.ItemNotFoundException;
import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.ItemImage;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ImageService imageService;

    private final ItemRepository itemRepository;

    public ItemService(ImageService imageService, ItemRepository itemRepository) {
        this.imageService = imageService;
        this.itemRepository = itemRepository;
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

    public void delete(UUID itemID, String username) throws ItemNotFoundException {
        Item item = itemRepository.findByIdAndOwner(itemID, new User.Builder().setUsername(username).build());
        if(item == null){
            throw new ItemNotFoundException("Item does not exist");
        }
        itemRepository.deleteById(itemID);
    }
}
