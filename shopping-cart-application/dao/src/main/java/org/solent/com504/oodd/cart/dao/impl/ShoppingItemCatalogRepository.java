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
package org.solent.com504.oodd.cart.dao.impl;

import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Image;
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
    
    @Query("select i from ShoppingItem i where enabled = 'true'")
    public List<ShoppingItem> getAvailable ();
    
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
    
    
}
