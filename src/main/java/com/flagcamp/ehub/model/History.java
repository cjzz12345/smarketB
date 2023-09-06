package com.flagcamp.ehub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class History {

    @Id
    @GeneratedValue
    private UUID order_id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User buyer;

    private String details;

    private LocalDateTime checkOutTime;

    public History(){}

    public UUID getOrder_id() {
        return order_id;
    }

    public History setOrder_id(UUID order_id) {
        this.order_id = order_id;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public History setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public History setDetails(String details) {
        this.details = details;
        return this;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public History setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }
}
