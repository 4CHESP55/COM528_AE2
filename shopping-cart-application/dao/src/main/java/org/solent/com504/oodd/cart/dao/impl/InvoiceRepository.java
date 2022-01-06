package org.solent.com504.oodd.cart.dao.impl;


import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long>{
    
    @Query("select i from Invoice i where i.purchaser.id = :id")
    public List<Invoice> findByUserId(@Param("id")Long id);
    
    @Query("select i from Invoice i where i.invoiceNumber = :oId")
    public List<Invoice> findByInvoiceNumber(@Param("oId")String oId);
    
}