package com.cognizant.InventoryService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Optional<Inventory> getInventory(Long productId) {
        return repository.findById(productId);
    }

    public Inventory updateInventory(Long productId, Integer quantity) {
        Inventory inventory = repository.findById(productId)
            .orElse(new Inventory());
        inventory.setProductId(productId);
        inventory.setQuantity(quantity);
        return repository.save(inventory);
    }
}
