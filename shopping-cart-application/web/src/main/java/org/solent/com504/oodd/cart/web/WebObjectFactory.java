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
package org.solent.com504.oodd.cart.web;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.solent.com504.oodd.cart.service.ShoppingServiceImpl;
import org.solent.com504.oodd.cart.service.ShoppingCartImpl;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;

public class WebObjectFactory {

    static ShoppingService shoppingService = ServiceObjectFactory.getShoppingService();
    static Orders orders = ServiceObjectFactory.getOrders();
    
    // cannot instantiate
    private WebObjectFactory(){
        
    }
    
    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);
    private static PropertiesDao propertiesDao = null;

    public static PropertiesDao getPropertiesDao() {
        if (propertiesDao == null) {
            synchronized (WebObjectFactory.class) {
                if (propertiesDao == null) {
                    // creates a single instance of the dao
                    String TEMP_DIR = System.getProperty("java.io.tmpdir");
                    File propertiesFile = new File(TEMP_DIR + "/application.properties");
                    LOG.debug("using system temp directory: " + TEMP_DIR);
                    LOG.debug("using application properties file : " + propertiesFile.getAbsolutePath());
                    propertiesDao = new PropertiesDao(propertiesFile.getAbsolutePath(), "default.properties");
                }
            }
        }
        return propertiesDao;
    }
    
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    public static Orders getOrders(){
        return orders;
    }
    
    public static ShoppingCart getNewShoppingCart(){
        return ServiceObjectFactory.getNewShoppingCart();
    }
    
}
