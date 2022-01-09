/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.solent.com504.oodd.cart.service.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;

/**
 *
 * @author Admin
 */
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
