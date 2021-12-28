package org.solent.com504.oodd.cart.dao.impl;


import java.util.List;
import org.solent.com504.oodd.cart.model.dto.ShoppingItemDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShoppingItemDescriptionRepository  extends JpaRepository<ShoppingItemDescription,Long>{
    
    @Query("select i from ShoppingItemDescription i where itemId = :itemId")
    public List<ShoppingItemDescription> getById (@Param ("itemId")Long itemId);
    
    @Query("select i from ShoppingItemDescription i where enabled = 'true'")
    public List<ShoppingItemDescription> findEnabled ();
    
    @Transactional
    @Modifying
    @Query("update ShoppingItemDescription i set i.description = :description, i.image = :image where i.itemId = :itemId")
    public void updateById (@Param ("description")String description, @Param ("image")Long image, @Param("itemId")Long itemId);
    
}