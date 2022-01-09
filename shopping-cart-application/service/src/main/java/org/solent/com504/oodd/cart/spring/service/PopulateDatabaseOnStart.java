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
package org.solent.com504.oodd.cart.spring.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateDatabaseOnStart {

    private static final Logger LOG = LogManager.getLogger(PopulateDatabaseOnStart.class);

    private static final String DEFAULT_ADMIN_USERNAME = "globaladmin";
    private static final String DEFAULT_ADMIN_PASSWORD = "globaladmin";

    private static final String DEFAULT_USER_PASSWORD = "user1234";
    private static final String DEFAULT_USER_USERNAME = "user1234";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @PostConstruct
    public void initDatabase() {
        LOG.debug("initialising database with startup values");

        // initialising admin and normal user if dont exist
        User adminUser = new User();
        adminUser.setUsername(DEFAULT_ADMIN_USERNAME);
        adminUser.setFirstName("default administrator");
        adminUser.setPassword(DEFAULT_ADMIN_PASSWORD);
        adminUser.setUserRole(UserRole.ADMINISTRATOR);

        List<User> users = userRepository.findByUsername(DEFAULT_ADMIN_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(adminUser);
            LOG.info("creating new default admin user:" + adminUser);
        } else {
            LOG.info("default admin user already exists. Not creating new :" + adminUser);
        }

        User defaultUser = new User();
        defaultUser.setUsername(DEFAULT_USER_USERNAME);
        defaultUser.setFirstName("default user");
        defaultUser.setPassword(DEFAULT_USER_PASSWORD);
        defaultUser.setUserRole(UserRole.CUSTOMER);

        users = userRepository.findByUsername(DEFAULT_USER_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(defaultUser);
            LOG.info("creating new default user:" + defaultUser);
        } else {
            LOG.info("defaultuser already exists. Not creating new :" + defaultUser);
        }
        List<String> phoneCaseList = Arrays.asList(
                "Samsung Galaxy S21 Ultra Phone Case",
                "Samsung Galaxy S21 Phone Case",
                "Samsung Galaxy S21 Plus Phone Case",
                "OnePlus 9 Phone Case",
                "OnePlus Nord 2 5G Phone Case",
                "OnePlus 8T 5G Phone Case",
                "Samsung Galaxy S20 FE Phone Case",
                "Motorola Edge 20 Phone Case",
                "OnePlus 7T Pro Phone Case",
                "Apple iPhone 12 Phone Case",
                "Apple iPhone 12 Mini 5G Phone Case",
                "Apple iPhone 11 Phone Case",
                "Apple iPhone SE Phone Case"
        );
        
        List<String> phoneChargerList = Arrays.asList(
                "iPhone Phone Charger",
                "Android/USB-C Phone Charger",
                "Android/Micro-USB Phone Charger"
        );
        
        List<ShoppingItem> itemlist = new ArrayList();
        phoneCaseList.forEach(phoneCase -> {
            itemlist.add(new ShoppingItem(phoneCase, 10, 12.99, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true));
        });
        phoneChargerList.forEach(phoneCharger -> {
            itemlist.add(new ShoppingItem(phoneCharger, 10, 8.99, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true));
        });
        itemlist.forEach(item -> {
            shoppingItemCatalogRepository.save(item);
        });

        LOG.debug("database initialised");
    }
}
