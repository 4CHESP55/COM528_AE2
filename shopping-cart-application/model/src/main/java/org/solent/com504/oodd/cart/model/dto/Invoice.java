package org.solent.com504.oodd.cart.model.dto;

import java.util.Date;
import java.util.List;
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

    private List<ShoppingItem> purchasedItems = null;

    private User purchaser = null;
    
    private OrderStatus orderStatus = null;
    
    public Invoice(){
        
    }
    
    public Invoice(String invoiceNumber, 
            Date dateOfPurchase, 
            Double amountDue, 
            List<ShoppingItem> purchasedItems, 
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

    @Temporal(javax.persistence.TemporalType.DATE)
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

    @OneToMany
    public List<ShoppingItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<ShoppingItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    @OneToOne
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
