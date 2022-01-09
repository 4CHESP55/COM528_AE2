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
package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Image {

    @Lob
    @Column(columnDefinition="BLOB")
    byte[] content=null;

    String title=null;
    
    @Lob
    @Column(columnDefinition="CLOB")
    String base64image=null;
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }
    
    public byte[] getContent() {
        return content;
    }
    
   
    public void setBase64image(String base64Encoded) {
        this.base64image = base64Encoded;
    }
    
    public String getBase64image() {
        return base64image;
    }
}
