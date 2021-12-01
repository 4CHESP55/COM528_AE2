/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItemDescription;
import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingDescription {
    
        public List<ShoppingItemDescription> getItemDescriptions();
        
        public void addItemDescription(ShoppingItemDescription shoppingItemDescription);
        
        public void removeItemDescription(Long id);

}
