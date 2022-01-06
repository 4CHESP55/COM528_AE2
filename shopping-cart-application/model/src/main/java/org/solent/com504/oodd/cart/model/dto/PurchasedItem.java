/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Admin
 */
@Entity
public class PurchasedItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity=0;
    private Double price=0.0;
    @ManyToOne
    private ShoppingItem shoppingItem = null;
    
    public PurchasedItem(){
        
    }

    public PurchasedItem(Integer quantity, Double price, ShoppingItem shoppingItem) {
        this.quantity = quantity;
        this.price = price;
        this.shoppingItem = shoppingItem;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
    
    public void setShoppingItem(ShoppingItem shoppingItem) {
        this.shoppingItem = shoppingItem;
    }
    
}
