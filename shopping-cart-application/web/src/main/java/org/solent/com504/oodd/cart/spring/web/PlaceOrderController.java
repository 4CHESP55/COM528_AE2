/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.spring.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.web.PropertiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/")
public class PlaceOrderController {
    
    @Autowired
    PropertiesDao propertiesDao;
    
    
    @Autowired
    ShoppingService shoppingService = null;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;
    
    @Autowired
    ShoppingItemCatalogRepository shoppingItemCatalogRepository;

       
    @Autowired
    UserRepository userRepository;
    
    final static Logger LOG = LogManager.getLogger(PlaceOrderController.class);

    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser",sessionUser);
        }
        return sessionUser;
    }
    
    @RequestMapping(value = "/placeOrder", method = {RequestMethod.POST})
    public String viewPaymentSettings(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "URL", required = false) String URL,
            @RequestParam(name = "cardName", required = false) String cardName,
            @RequestParam(name = "cardNumber", required = false) String cardNumber,
            @RequestParam(name = "cardCvv", required = false) String cardCvv,
            @RequestParam(name = "expiryMonth", required = false) String expiryMonth,
            @RequestParam(name = "expiryYear", required = false) String expiryYear,
            
            Model model,
            HttpSession session) throws IOException {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "placeOrder");

        String message = "";
        String errorMessage = "";

        // get the to card details from the property file
        String toURL = propertiesDao.getProperty("org.solent.com504.oodd.bank.URL");
        String toCardName = propertiesDao.getProperty("org.solent.com504.oodd.payment.cardName");
        String toCreditCard = propertiesDao.getProperty("org.solent.com504.oodd.payment.creditCard");
        String toCvv = propertiesDao.getProperty("org.solent.com504.oodd.payment.cvv");
        String toExpiry = propertiesDao.getProperty("org.solent.com504.oodd.payment.expiry");
        String toIssue = propertiesDao.getProperty("org.solent.com504.oodd.payment.issue");
        
        // convert into credit card object
        CreditCard toCard = new CreditCard();
        toCard.setName(toCardName);
        toCard.setEndDate(toExpiry);
        toCard.setCardnumber(toCreditCard);
        toCard.setCvv(toCvv);
        toCard.setIssueNumber(toIssue);
        
        // create a new object for the from card as the user may have changed card details when checking out
        CreditCard fromCard = new CreditCard();
        fromCard.setName(cardName);
        String cardExpiry = expiryMonth + "/" + expiryYear;
        fromCard.setEndDate(cardExpiry);
        fromCard.setCardnumber(cardNumber);
        fromCard.setCvv(cardCvv);
        
        // initialise the bank client
        BankRestClient client = new BankRestClientImpl(toURL);
        
        // get the cart total to charge the card
        Double shoppingcartTotal = shoppingCart.getTotal();
 
        // do the transaction
        TransactionReplyMessage result = client.transferMoney(fromCard, toCard, shoppingcartTotal);
        
        // if failed return to the checkout page
        if (result.getStatus().equals(1)) {
            return "redirect:checkout";
        } else {
            List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
            // create order status object
            for (ShoppingItem shoppingCartItem: shoppingCartItems) {
                //add the item id, name, quantity, price to the order status object
            }
            // add the shipping address to the order status
            // add the card details without cvv
            // set the status to "order paid"
            
            // remove items from cart since they have been purchased
            
            // display confirmation page with option to cancel for an immediate refund
        }
        
        
        // populate model with values
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "placeOrder";
    }
    
}
