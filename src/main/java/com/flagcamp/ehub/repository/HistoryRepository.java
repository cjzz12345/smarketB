package com.flagcamp.ehub.repository;

import com.flagcamp.ehub.model.History;
import com.flagcamp.ehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, UUID> {
    List<History> findHistoryByBuyer(User user);
}
