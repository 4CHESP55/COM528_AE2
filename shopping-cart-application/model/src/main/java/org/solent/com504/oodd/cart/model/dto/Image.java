/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Admin
 */
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
