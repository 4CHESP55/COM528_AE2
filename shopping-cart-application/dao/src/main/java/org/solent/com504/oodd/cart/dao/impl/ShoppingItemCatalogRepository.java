package org.solent.com504.oodd.cart.dao.impl;

import java.util.List;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShoppingItemCatalogRepository  extends JpaRepository<ShoppingItem,Long>{
    
    @Query("select i from ShoppingItem i where name = :name")
    public List<ShoppingItem> getItemByName (@Param ("name")String name);
    
    @Query("select i from ShoppingItem i where name like %:search%")
    public List<ShoppingItem> searchAll (@Param ("search")String search);
    
    @Query("select i from ShoppingItem i where id = :id")
    public List<ShoppingItem> getItemById (@Param ("id")Long id);
    
    @Transactional
    @Modifying
    @Query("delete from ShoppingItem i where i.name = :name")
    public void removeItemByName (@Param ("name")String name);
    
    @Transactional
    @Modifying
    @Query("update ShoppingItem i set i.name = :name, i.price = :price, i.quantity = :quantity where i.id = :id")
    public void updateItemById (@Param ("id")Long id, @Param ("name")String name, @Param("quantity")Integer quantity, @Param("price")Double price);
    
}
