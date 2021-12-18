/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.solent.com504.oodd.cart.dao.impl.ImageDbRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemDescriptionRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
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
    
    @Autowired
    private ImageDbRepository imageDbRepository;
    
    private HashMap<String, Image> imageMap = new HashMap<String, Image>();
    
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
    public void updateItemDescription(ShoppingItemDescription shoppingItemDescription) {
        
        ShoppingItemDescription item = null;
        List<ShoppingItemDescription> items = shoppingItemDescriptionRepository.getByName(shoppingItemDescription.getName());
        
        if (!items.isEmpty()) {
            item = items.get(0);
            shoppingItemDescriptionRepository.updateByName(item.getDescription(), item.getImage(), item.getName());
        }
    }
    
    @Override
    public void removeItemDescription(Long id) {
        shoppingItemDescriptionRepository.deleteById(id);
    }
    
    @Override
    public List<Image> getImages() {
        List<Image> imagelist = imageDbRepository.findAll();
        imagelist.forEach(image -> {
            byte[] bytes = image.getContent();
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ShoppingDescriptionImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            image.setBase64image(base64Encoded);
        });
        
        return imagelist;
    }
    
    @Override
    public Image getImage(Long id) {
        Image image = null;
        List<Image> images = imageDbRepository.getImageById(id);
        
        if (!images.isEmpty()) {
            image = images.get(0);
        
        
            byte[] bytes = image.getContent();
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ShoppingDescriptionImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            image.setBase64image(base64Encoded);
       }
        
        return image;
    }

}
