/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.spring.web;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.solent.com504.oodd.cart.dao.impl.ImageDbRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.solent.com504.oodd.cart.model.service.ShoppingDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/")
public class ImageController {
    @Autowired
    ImageDbRepository imageDbRepository;
    
    @Autowired
    ShoppingDescription shoppingDescription = null;
    
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public String handleUpload(@RequestParam("file") MultipartFile file, @RequestParam("itemId") Long itemId, Model model,
            HttpSession session) throws Exception {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Image dbImage = new Image();
            dbImage.setName(file.getOriginalFilename());
            dbImage.setContent(bytes);
            
            imageDbRepository.save(dbImage);
        }
        
        
        
        List<Image> image = shoppingDescription.getImages();

        // populate model with values
        model.addAttribute("images", image);
        return "upload";
    }

}
