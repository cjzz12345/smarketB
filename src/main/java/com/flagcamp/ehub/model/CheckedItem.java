package com.flagcamp.ehub.model;

import java.util.UUID;

public class CheckedItem {
    private UUID id;
    private int count;

    public CheckedItem(UUID id, int count) {
        this.id = id;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
