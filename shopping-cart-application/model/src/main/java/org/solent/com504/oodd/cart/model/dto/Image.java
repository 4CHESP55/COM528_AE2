/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Admin
 */
@Entity
public class Image {
    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    String name;
    
    @Lob
    String base64image;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }
    
    public byte[] getContent() {
        return content;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setBase64image(String base64Encoded) {
        this.base64image = base64Encoded;
    }
    
    public String getBase64image() {
        return base64image;
    }
}
