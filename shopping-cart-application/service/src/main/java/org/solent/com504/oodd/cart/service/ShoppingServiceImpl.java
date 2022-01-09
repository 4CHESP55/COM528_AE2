/*
 * Copyright 2022 Paul Chester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.com504.oodd.cart.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.tomcat.util.codec.binary.Base64;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Override
    public List<ShoppingItem> getAvailableItems() {
        List<ShoppingItem> itemlist = shoppingItemCatalogRepository.getAvailable();
        return itemlist;
    }
    
    @Override
    public List<ShoppingItem> getAllItems() {
        List<ShoppingItem> itemlist = shoppingItemCatalogRepository.findAll();
        return itemlist;
    }
    
    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            String base64Encoded = new String(encodeBase64, "UTF-8");
            image.setTitle(file.getOriginalFilename());
            image.setContent(bytes);
            image.setBase64image(base64Encoded);         
        }
        
        return image;
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
    
}
