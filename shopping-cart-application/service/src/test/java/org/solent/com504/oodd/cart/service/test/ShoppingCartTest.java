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
package org.solent.com504.oodd.cart.service.test;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.service.ShoppingCartImpl;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;

public class ShoppingCartTest {
    
    ShoppingCart shoppingCart = null;
    
    @Before
    public void setUp() {
        shoppingCart = ServiceObjectFactory.getNewShoppingCart();
        shoppingCart.getShoppingCartItems().clear();
    }

    @Test
    public void test1() {
        assertNotNull(shoppingCart);
    }

    /**
     * Test of getShoppingCartItems method, of class ShoppingCartImpl.
     */
    @Test
    public void testGetShoppingCartItems() {
        System.out.println("getShoppingCartItems");
        ShoppingCartImpl instance = new ShoppingCartImpl();
        List<ShoppingItem> result = instance.getShoppingCartItems();
        assertTrue(result.isEmpty());
        
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        
        instance.addItemToCart(shoppingItem, 1);
        
        assertEquals(1, instance.getShoppingCartItems().size() );

    }

    /**
     * Test of addItemToCart method, of class ShoppingCartImpl.
     */
    @Test
    public void testAddItemToCart() {
        System.out.println("addItemToCart");
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        Integer buy_quantity = 3;
        ShoppingCartImpl instance = new ShoppingCartImpl();
        instance.addItemToCart(shoppingItem, buy_quantity);
        List<ShoppingItem> result = instance.getShoppingCartItems();
        Integer item_quantity = 0;
        for (ShoppingItem item : result){
            item_quantity = item.getQuantity();
        }
        assertEquals(buy_quantity, item_quantity);
        
    }

    /**
     * Test of removeItemFromCart method, of class ShoppingCartImpl.
     */
    @Test
    public void testRemoveItemFromCart() {
        System.out.println("removeItemFromCart");
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        Integer buy_quantity = 3;
        ShoppingCartImpl instance = new ShoppingCartImpl();
        instance.addItemToCart(shoppingItem, buy_quantity);
        String itemUuid = "";
        List<ShoppingItem> result = instance.getShoppingCartItems();
        for (ShoppingItem item : result){
            itemUuid = item.getUuid();
        }
        assertEquals(1, instance.getShoppingCartItems().size() );
        instance.removeItemFromCart(itemUuid);
        assertEquals(0, instance.getShoppingCartItems().size() );
    }

    /**
     * Test of removeAllFromCart method, of class ShoppingCartImpl.
     */
    @Test
    public void testRemoveAllFromCart() {
        System.out.println("removeAllFromCart");
        ShoppingCartImpl instance = new ShoppingCartImpl();
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        shoppingItem.setId(1L);
        Integer buy_quantity = 3;
        instance.addItemToCart(shoppingItem, buy_quantity);   
        assertEquals(1, instance.getShoppingCartItems().size() );
        instance.removeAllFromCart();
        assertTrue(instance.getShoppingCartItems().isEmpty());
    }

    /**
     * Test of reduceItemFromCart method, of class ShoppingCartImpl.
     */
    @Test
    public void testReduceItemFromCart() {
        System.out.println("reduceItemFromCart");
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        Integer buy_quantity = 3;
        ShoppingCartImpl instance = new ShoppingCartImpl();
        instance.addItemToCart(shoppingItem, buy_quantity);
        String itemUuid = "";
        List<ShoppingItem> result = instance.getShoppingCartItems();
        Integer item_quantity = 0;
        for (ShoppingItem item : result){
            itemUuid = item.getUuid();
            item_quantity = item.getQuantity();
        }
        assertEquals(buy_quantity, item_quantity);
        instance.reduceItemFromCart(itemUuid);
        result = instance.getShoppingCartItems();
        for (ShoppingItem item : result){
            itemUuid = item.getUuid();
            item_quantity = item.getQuantity();
        }
        assertEquals(Integer.valueOf(2), item_quantity);
    }

    /**
     * Test of increaseItemFromCart method, of class ShoppingCartImpl.
     */
    @Test
    public void testIncreaseItemFromCart() {
        System.out.println("increaseItemFromCart");
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        Integer buy_quantity = 3;
        ShoppingCartImpl instance = new ShoppingCartImpl();
        instance.addItemToCart(shoppingItem, buy_quantity);
        String itemUuid = "";
        List<ShoppingItem> result = instance.getShoppingCartItems();
        Integer item_quantity = 0;
        for (ShoppingItem item : result){
            itemUuid = item.getUuid();
            item_quantity = item.getQuantity();
        }
        assertEquals(buy_quantity, item_quantity);
        instance.increaseItemFromCart(itemUuid);
        result = instance.getShoppingCartItems();
        for (ShoppingItem item : result){
            itemUuid = item.getUuid();
            item_quantity = item.getQuantity();
        }
        assertEquals(Integer.valueOf(4), item_quantity);

    }

    /**
     * Test of getTotal method, of class ShoppingCartImpl.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        ShoppingCartImpl instance = new ShoppingCartImpl();
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        shoppingItem.setPrice(10.00);
        Integer buy_quantity = 3;
        instance.addItemToCart(shoppingItem, buy_quantity);
        double result = instance.getTotal();
        assertEquals(30.00, result, 0.0);

    }
    
}
