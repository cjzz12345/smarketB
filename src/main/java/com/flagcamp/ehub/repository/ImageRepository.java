package com.flagcamp.ehub.repository;

import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ItemImage, String> {

    List<ItemImage> findByItem(Item item);
}
