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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;

public class ShoppingCartImpl implements ShoppingCart {

    private HashMap<String, ShoppingItem> itemMap = new HashMap<String, ShoppingItem>();

    @Override
    public List<ShoppingItem> getShoppingCartItems() {
        List<ShoppingItem> itemlist = new ArrayList();
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            itemlist.add(shoppingCartItem);
        }
        return itemlist;
    }

    @Override
    public void addItemToCart(ShoppingItem shoppingItem, Integer buy_quantity) {
        // itemMap.put(shoppingItem.getUuid(), shoppingItem);
        
        // ANSWER
        boolean itemExists = false;
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            if (shoppingCartItem.getId().equals(shoppingItem.getId())){
                Integer q = shoppingCartItem.getQuantity();
                shoppingCartItem.setQuantity(q+buy_quantity);
                itemExists = true;
                break;
            }
        }
        if (!itemExists){
            shoppingItem.setQuantity(buy_quantity);
            itemMap.put(shoppingItem.getUuid(), shoppingItem);
        }
    }

    @Override
    public void removeItemFromCart(String itemUuid) {
        itemMap.remove(itemUuid);
    }
    
    @Override
    public void removeAllFromCart() {
        itemMap.clear();
        itemMap = new HashMap<>();
    }
    
    @Override
    public void reduceItemFromCart(String itemUuid) {
        ShoppingItem shoppingCartItem = itemMap.get(itemUuid);
        Integer quantity = shoppingCartItem.getQuantity();
        if (quantity > 1){
            shoppingCartItem.setQuantity(quantity-1);
        } else {
            itemMap.remove(itemUuid);
        }
        
    }
    
    @Override
    public void increaseItemFromCart(String itemUuid) {
        ShoppingItem shoppingCartItem = itemMap.get(itemUuid);
        Integer quantity = shoppingCartItem.getQuantity();
        shoppingCartItem.setQuantity(quantity+1);
    }

    @Override
    public double getTotal() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // ANSWER
        double total = 0;

        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            total = total + shoppingCartItem.getPrice() * shoppingCartItem.getQuantity();
        }

        return total;

    }

}
