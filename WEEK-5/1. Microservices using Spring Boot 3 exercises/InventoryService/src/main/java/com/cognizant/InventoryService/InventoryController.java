package com.cognizant.InventoryService;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public Optional<Inventory> getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }

    @PostMapping("/{productId}")
    public Inventory updateInventory(@PathVariable Long productId, @RequestParam Integer quantity) {
        return service.updateInventory(productId, quantity);
    }

}
