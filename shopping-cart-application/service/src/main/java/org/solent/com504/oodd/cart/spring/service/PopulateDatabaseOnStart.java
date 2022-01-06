/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.service;

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

/**
 *
 * @author cgallen
 */
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
                
        List<ShoppingItem> itemlist = Arrays.asList(new ShoppingItem("house", 10, 20000.00, null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true),
            new ShoppingItem("hen", 10, 5.00, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true),
            new ShoppingItem("car", 10, 5000.00, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true),
            new ShoppingItem("pet alligator", 10, 65.00, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true),
            new ShoppingItem("tree", 10, 150000.00, new Image(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam. \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra felis non magna blandit, nec scelerisque ligula semper. Suspendisse et orci at leo varius mattis eu et velit. Nam venenatis nunc enim, ornare porttitor lectus tempor sit amet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam eleifend lorem in maximus dapibus. Vestibulum aliquam libero in erat vestibulum, eu tristique ipsum suscipit. Vestibulum ex neque, euismod tincidunt justo non, semper aliquam felis. Duis at porttitor neque. Nulla felis felis, ultrices a lacus eget, imperdiet fermentum mauris. Nunc sed dolor ac massa aliquet tristique non ac diam.", true)
        );
        
        itemlist.forEach(item -> {
            shoppingItemCatalogRepository.save(item);
        });

        LOG.debug("database initialised");
    }
}
