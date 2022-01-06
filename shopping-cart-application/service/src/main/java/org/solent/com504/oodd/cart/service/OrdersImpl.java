/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.service;

import java.util.List;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class OrdersImpl implements Orders {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Override
    public List<Invoice> getOrdersById(Long id){
        List<Invoice> orderList = invoiceRepository.findByUserId(id);
        return orderList;
    }

    @Override
    public Invoice getOrderByInvoiceNumber(String oId){
        Invoice order = null;
        List<Invoice> orders = invoiceRepository.findByInvoiceNumber(oId);

        if (!orders.isEmpty()) {
            order = orders.get(0);
        }

        return order;
    }

    
}
