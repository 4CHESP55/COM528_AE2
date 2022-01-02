/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import java.io.IOException;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public List<ShoppingItem> getAllItems();
        
        public Image saveImage(MultipartFile file) throws IOException;
        
        public List<ShoppingItem> getSearchedItems(String search);
        
        public boolean purchaseItems(ShoppingCart shoppingCart);
        
        public ShoppingItem getNewItemByName(String name);
        
        public ShoppingItem getNewItemById(Long id);
        
        public void removeItemByName(String name);
        
        public void addItemToCatalog(ShoppingItem shoppingItem);
        
}
