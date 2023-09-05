package com.flagcamp.ehub.repository;

import com.flagcamp.ehub.model.Item;
import com.flagcamp.ehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Item findByIdAndOwner(UUID id, User user);

    Item findItemById(UUID id);

    List<Item> findByName(String itemName);
}