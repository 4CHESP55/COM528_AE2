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
    
    @Query("select i from ShoppingItemDescription i where name = :name")
    public List<ShoppingItemDescription> getByName (@Param ("name")String name);
    
    @Transactional
    @Modifying
    @Query("update ShoppingItemDescription i set i.description = :description, i.image = :image where i.name = :name")
    public void updateByName (@Param ("description")String description, @Param ("image")Long image, @Param("name")String name);
    
}