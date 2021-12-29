/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Override
    public List<ShoppingItem> getAvailableItems() {
        List<ShoppingItem> itemlist = shoppingItemCatalogRepository.findAll();
        return itemlist;
    }
    
    @Override
    public List<ShoppingItem> getSearchedItems(String search) {
        List<ShoppingItem> itemlist = shoppingItemCatalogRepository.searchAll(search);
        return itemlist;
    }

    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart) {
        System.out.println("purchased items");
        for (ShoppingItem shoppingItem : shoppingCart.getShoppingCartItems()) {
            System.out.println(shoppingItem);
        }

        return true;
    }

    @Override
    public ShoppingItem getNewItemByName(String name) {
        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemByName(name);
        
        if (!items.isEmpty()) {
            item = items.get(0);
            item.setUuid(UUID.randomUUID().toString());
        }
        
        return item;
    }
    
    @Override
    public ShoppingItem getNewItemById(Long id) {
        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemById(id);
        
        if (!items.isEmpty()) {
            item = items.get(0);
            item.setUuid(UUID.randomUUID().toString());
        }
        
        return item;
    }
    
    @Override
    public void removeItemByName(String name) {
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemByName(name);
        
        if (!items.isEmpty()) {
            shoppingItemCatalogRepository.removeItemByName(name);
        }
    }
    
    @Override
    public void addItemToCatalog(ShoppingItem shoppingItem) {
        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemByName(shoppingItem.getName());

        if (!items.isEmpty()) {
            throw new IllegalArgumentException("Item " +shoppingItem+" already in Catalog");
        }
        item = shoppingItemCatalogRepository.save(shoppingItem);
    }
    
    @Override
    public void updateItemById(ShoppingItem shoppingItem) {
        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemById(shoppingItem.getId());
        
        if (!items.isEmpty()) {
            item = items.get(0);
            shoppingItemCatalogRepository.updateItemById(item.getId(), item.getName(), item.getQuantity(), item.getPrice());
        }
    }

}
