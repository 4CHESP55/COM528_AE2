/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.solent.com504.oodd.cart.model.service;

import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Invoice;

/**
 *
 * @author Admin
 */
public interface Orders {
    
    public List<Invoice> getOrdersById(Long id);
    
    public Invoice getOrderByInvoiceNumber(String oId);
}
