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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;

public class OrdersImplTest {
    
    Orders orders = null;

    @Before
    public void before(){
        orders = ServiceObjectFactory.getOrders();
        
    }
    
    @Test
    public void test1() {
        assertNotNull(orders);
    }
    
}
