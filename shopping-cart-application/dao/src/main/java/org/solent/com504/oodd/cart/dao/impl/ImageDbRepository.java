/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.solent.com504.oodd.cart.dao.impl;

import org.solent.com504.oodd.cart.model.dto.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository

public interface ImageDbRepository extends JpaRepository<Image, Long> {
    
}
