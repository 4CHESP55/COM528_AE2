/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public boolean purchaseItems(ShoppingCart shoppingCart);
        
        public ShoppingItem getNewItemByName(String name);
        
        public ShoppingItem getNewItemById(Long id);
        
        public void removeItemByName(String name);
        
        public void addItemToCatalog(ShoppingItem shoppingItem);
        
        public void updateItemById(ShoppingItem shoppingItem);

}
