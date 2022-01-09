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
package org.solent.com504.oodd.cart.model.service;

import java.io.IOException;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public List<ShoppingItem> getAllItems();
        
        public Image saveImage(MultipartFile file) throws IOException;
        
        public List<ShoppingItem> getSearchedItems(String search);
        
        public boolean purchaseItems(ShoppingCart shoppingCart);
        
        public ShoppingItem getNewItemByName(String name);
        
        public ShoppingItem getNewItemById(Long id);
        
        public void removeItemByName(String name);
        
        public void addItemToCatalog(ShoppingItem shoppingItem);
        
}
