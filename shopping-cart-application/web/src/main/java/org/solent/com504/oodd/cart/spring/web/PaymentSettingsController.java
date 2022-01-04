/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.spring.web;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.solent.com504.oodd.cart.web.PropertiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/")
public class PaymentSettingsController {
    
    @Autowired
    PropertiesDao propertiesDao;

    final static Logger LOG = LogManager.getLogger(PaymentSettingsController.class);

    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser", sessionUser);
        }
        return sessionUser;
    }

    @RequestMapping(value = "/paymentSettings", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewPaymentSettings(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "URL", required = false) String URL,
            @RequestParam(name = "cardName", required = false) String cardName,
            @RequestParam(name = "creditCard", required = false) String creditCard,
            @RequestParam(name = "cvv", required = false) String cvv,
            @RequestParam(name = "expiryMonth", required = false) String expiryMonth,
            @RequestParam(name = "expiryYear", required = false) String expiryYear,
            @RequestParam(name = "issue", required = false) String issue,
            Model model,
            HttpSession session) throws IOException {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "paymentSettings");

        String message = "";
        String errorMessage = "";

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            return "redirect:home";
        }

              
        if ("saveSettings".equals(action)) {
        propertiesDao.setProperty("org.solent.com504.oodd.bank.URL", URL);
        propertiesDao.setProperty("org.solent.com504.oodd.payment.cardName", cardName);
        propertiesDao.setProperty("org.solent.com504.oodd.payment.creditCard", creditCard);
        propertiesDao.setProperty("org.solent.com504.oodd.payment.cvv", cvv);
        String expiryDate = expiryMonth + "/" + expiryYear;
        propertiesDao.setProperty("org.solent.com504.oodd.payment.expiry", expiryDate);
        propertiesDao.setProperty("org.solent.com504.oodd.payment.issue", issue);
        }

        String cURL = propertiesDao.getProperty("org.solent.com504.oodd.bank.URL");
        String cCardName = propertiesDao.getProperty("org.solent.com504.oodd.payment.cardName");
        String cCreditCard = propertiesDao.getProperty("org.solent.com504.oodd.payment.creditCard");
        String cCvv = propertiesDao.getProperty("org.solent.com504.oodd.payment.cvv");
        String cExpiry = propertiesDao.getProperty("org.solent.com504.oodd.payment.expiry");
        String cIssue = propertiesDao.getProperty("org.solent.com504.oodd.payment.issue");
        
        
        // populate model with values
        model.addAttribute("cURL", cURL);
        model.addAttribute("cCardName", cCardName);
        model.addAttribute("cCreditCard", cCreditCard);
        model.addAttribute("cCvv", cCvv);
        model.addAttribute("cExpiry", cExpiry);
        model.addAttribute("cIssue", cIssue);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "paymentSettings";
    }

}
