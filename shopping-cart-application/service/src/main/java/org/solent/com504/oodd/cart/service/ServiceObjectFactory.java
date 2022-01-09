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

import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;

public class ServiceObjectFactory {
    
    static ShoppingService shoppingService = new ShoppingServiceImpl();
    static Orders orders = new OrdersImpl();
    
    // cannot instantiate
    private ServiceObjectFactory(){
        
    }
    
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    public static Orders getOrders(){
        return orders;
    }
    
    public static ShoppingCart getNewShoppingCart(){
        return new ShoppingCartImpl();
    }
    
}
