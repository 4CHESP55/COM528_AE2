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
package org.solent.com504.oodd.cart.service;

import java.util.List;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
