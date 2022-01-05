/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.com504.oodd.cart.spring.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Address;
import org.solent.com504.oodd.cart.model.dto.CardType;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.dto.OrderStatus;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.Orders;
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
    Orders orders = null;

    @Autowired
    ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    final static Logger LOG = LogManager.getLogger(PlaceOrderController.class);

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

    @RequestMapping(value = "/placeOrder", method = {RequestMethod.POST})
    public String viewPaymentSettings(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "orderId", required = false) String orderId,
            @RequestParam(name = "URL", required = false) String URL,
            @RequestParam(name = "cardName", required = false) String cardName,
            @RequestParam(name = "cardNumber", required = false) String cardNumber,
            @RequestParam(name = "cardCvv", required = false) String cardCvv,
            @RequestParam(name = "expiryMonth", required = false) String expiryMonth,
            @RequestParam(name = "expiryYear", required = false) String expiryYear,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "houseNumber", required = false) String houseNumber,
            @RequestParam(value = "addressLine1", required = false) String addressLine1,
            @RequestParam(value = "addressLine2", required = false) String addressLine2,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "county", required = false) String county,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "postcode", required = false) String postcode,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "cardType", required = false) String cardType,
            Model model,
            HttpSession session) throws IOException {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "placeOrder");

        String message = "";
        String errorMessage = "";

        Long id = null;
        String oId = null;
        Invoice orderToShow = null;
        
        User checkoutUser = null;

        if ("checkout".equals(action)) {

            if (!UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
                List<User> userList = userRepository.findByUsername(sessionUser.getUsername());
                checkoutUser = userList.get(0);
            } else {
                checkoutUser = new User();
            }

            // set details from the address tab on the checkout page
            checkoutUser.setFirstName(firstName);
            checkoutUser.setSecondName(secondName);
            Address address = new Address();
            address.setHouseNumber(houseNumber);
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setCity(city);
            address.setCounty(county);
            address.setCountry(country);
            address.setPostcode(postcode);
            address.setMobile(mobile);
            address.setTelephone(telephone);
            checkoutUser.setAddress(address);

            // set details from the payment tab on the checkout page
            CreditCard creditCard = new CreditCard();
            creditCard.setName(cardName);
            creditCard.setCardnumber(cardNumber);
            String cardEndDate = expiryMonth + "/" + expiryYear;
            creditCard.setEndDate(cardEndDate);
            checkoutUser.setCreditCard(creditCard);

            CardType type = CardType.valueOf(cardType);
            checkoutUser.setCardType(type);

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

            // get the card details just set for the checkout user
            CreditCard fromCard = checkoutUser.getCreditCard();
            // only set the cvv for the object used for the transaction, not the checkout user
            fromCard.setCvv(cardCvv);

            // initialise the bank client
            BankRestClient client = new BankRestClientImpl(toURL);

            // get the cart total to charge the card
            Double shoppingcartTotal = shoppingCart.getTotal();

            // get all the cart items
            List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

            // Check there is enough stock (someone could have checked out previously)
            Boolean cartChange = false;
            for (ShoppingItem cartItem : shoppingCartItems) {

                ShoppingItem item = shoppingService.getNewItemById(cartItem.getId());

                if (item.getQuantity() < cartItem.getQuantity()) {
                    cartItem.setQuantity(item.getQuantity());
                    if (item.getQuantity() == 0) {
                        shoppingCart.removeItemFromCart(cartItem.getUuid());
                    }
                    cartChange = true;
                }
            }

            // if the cart has changed due to stock redirect to cart
            if (cartChange) {
                return "redirect:checkout";
            }

            // do the transaction
            TransactionReplyMessage result = client.transferMoney(fromCard, toCard, shoppingcartTotal);

            // if failed return to the checkout page
            if (result.getStatus().equals(1)) {
                return "redirect:checkout";
            } else {
                Invoice order = new Invoice();
                order.setPurchasedItems(shoppingCartItems);
                order.setAmountDue(shoppingcartTotal);
                OrderStatus status = OrderStatus.valueOf("PAID");
                order.setOrderStatus(status);
                Date date = java.util.Calendar.getInstance().getTime();
                order.setDateOfPurchase(date);
                order.setPurchaser(checkoutUser);
                order.setInvoiceNumber(result.getTransactionId());
                oId = result.getTransactionId();

                // Save order to repository
                invoiceRepository.save(order);

                // reduce stock of each item purchased
                order.getPurchasedItems().stream().map(purchasedItem -> {
                    ShoppingItem item = shoppingService.getNewItemById(purchasedItem.getId());
                    item.setQuantity(item.getQuantity() - purchasedItem.getQuantity());
                    return item;
                }).forEachOrdered(item -> {
                    shoppingItemCatalogRepository.save(item);
                });

                // clear the shopping cart
                shoppingCart.removeAllFromCart();

                // display confirmation page with option to cancel for an immediate refund
            }
        }
        
        if (userId == null) {
            if (!sessionUser.getUserRole().equals(UserRole.ANONYMOUS)) {
                id = sessionUser.getId();
            } else if (checkoutUser != null){
                id = checkoutUser.getId();
            }else {
                return "redirect:home";
            }
        } else {
            id = Long.parseLong(userId);
        }

        if (oId == null && orderId == null) {
            return "redirect:home";
        } else {
            oId = orderId;
        }
        
        List<Invoice> viewOrders = orders.getOrdersById(id);
        List<ShoppingItem> orderItems = null;

        for (Invoice order : viewOrders) {
            if (order.getInvoiceNumber().equals(oId)) {
                orderToShow = order;
                orderItems = orderToShow.getPurchasedItems();
            }
        }
      
        // populate model with values
        model.addAttribute("orderToShow", orderToShow);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "placeOrder";
    }

}