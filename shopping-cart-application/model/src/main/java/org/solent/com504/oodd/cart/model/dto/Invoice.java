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
package org.solent.com504.oodd.cart.model.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Invoice {

    private Long id;

    private String invoiceNumber = "";

    private Date dateOfPurchase = null;

    private Double amountDue = 0.0;

    private List<PurchasedItem> purchasedItems = null;

    private User purchaser = null;
    
    private OrderStatus orderStatus = null;
    
    public Invoice(){
        
    }
    
    public Invoice(String invoiceNumber, 
            Date dateOfPurchase, 
            Double amountDue, 
            List<PurchasedItem> purchasedItems, 
            User purchaser, 
            OrderStatus orderStatus) {
        
        this.invoiceNumber = invoiceNumber;
        this.dateOfPurchase = dateOfPurchase;
        this.amountDue = amountDue;
        this.purchasedItems = purchasedItems;
        this.purchaser = purchaser;
        this.orderStatus = orderStatus;
    
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<PurchasedItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public User getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    @Override
    public String toString() {
        return "Invoice{" + "invoiceNumber=" + invoiceNumber + "dateOfPurchase=" + dateOfPurchase + "amountDue=" + amountDue + "purchasedItems=" + purchasedItems + "purchaser=" + purchaser + "orderStatus=" + orderStatus + '}';
    }

}
