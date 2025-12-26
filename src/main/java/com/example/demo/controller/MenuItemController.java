package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(
            @RequestBody MenuItem item) {

        return new ResponseEntity<>(
                menuItemService.createMenuItem(item),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItem item) {

        return ResponseEntity.ok(
                menuItemService.updateMenuItem(id, item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItem(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                menuItemService.getMenuItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(
                menuItemService.getAllMenuItems());
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateMenuItem(@PathVariable Long id) {
        menuItemService.deactivateMenuItem(id);
    }
}
