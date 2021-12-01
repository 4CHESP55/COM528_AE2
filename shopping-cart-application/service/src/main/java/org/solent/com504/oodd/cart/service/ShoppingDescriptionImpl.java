/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemDescriptionRepository;
import org.solent.com504.oodd.cart.model.service.ShoppingDescription;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.ShoppingItemDescription;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class ShoppingDescriptionImpl implements ShoppingDescription {
    @Autowired
    private ShoppingItemDescriptionRepository shoppingItemDescriptionRepository;

    @Override
    public List<ShoppingItemDescription> getItemDescriptions() {
        List<ShoppingItemDescription> descriptionlist = shoppingItemDescriptionRepository.findAll();
        return descriptionlist;
    }

    @Override
    public void addItemDescription(ShoppingItemDescription shoppingItemDescription) {
        shoppingItemDescriptionRepository.save(shoppingItemDescription);
    }

    @Override
    public void removeItemDescription(Long id) {
        shoppingItemDescriptionRepository.deleteById(id);
    }

}
