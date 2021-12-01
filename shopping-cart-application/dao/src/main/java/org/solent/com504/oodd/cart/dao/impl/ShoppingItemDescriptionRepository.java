package org.solent.com504.oodd.cart.dao.impl;


import org.solent.com504.oodd.cart.model.dto.ShoppingItemDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemDescriptionRepository  extends JpaRepository<ShoppingItemDescription,Long>{
    
}