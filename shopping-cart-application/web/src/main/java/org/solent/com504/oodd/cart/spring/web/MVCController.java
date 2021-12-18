package org.solent.com504.oodd.cart.spring.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ImageDbRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.ShoppingItemDescription;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingDescription;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.web.WebObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class MVCController {

    final static Logger LOG = LogManager.getLogger(MVCController.class);

    // this could be done with an autowired bean
    //private ShoppingService shoppingService = WebObjectFactory.getShoppingService();
    @Autowired
    ShoppingService shoppingService = null;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;
    
    @Autowired
    ShoppingDescription shoppingDescription = null;
    
    @Autowired
    ImageDbRepository imageDbRepository;

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

    // this redirects calls to the root of our application to index.html
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewShop(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "home");

        String message = "";
        String errorMessage = "";

        // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
//        if (shoppingCart == null) synchronized (this) {
//            if (shoppingCart == null) {
//                shoppingCart = WebObjectFactory.getNewShoppingCart();
//                session.setAttribute("shoppingCart", shoppingCart);
//            }
//        }
        if (action == null) {
            // do nothing but show page
        } else if ("addItemToCart".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
            if (shoppingItem == null) {
                message = "cannot add unknown " + itemName + " to cart";
            } else {
                message = "adding " + itemName + " to cart price= " + shoppingItem.getPrice();
                shoppingCart.addItemToCart(shoppingItem);
            }
        } else if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUuid);
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        
        List<ShoppingItemDescription> shoppingItemDescriptions = shoppingDescription.getItemDescriptions();

        Double shoppingcartTotal = shoppingCart.getTotal();

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingItemDescriptions", shoppingItemDescriptions);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "home";
    }
    
    @RequestMapping(value = "/catalog", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCatalog(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name= "file", required = false) MultipartFile file,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemPrice", required = false) String itemPrice,
            @RequestParam(name = "itemQuantity", required = false) String itemQuantity,
            Model model,
            HttpSession session) throws IOException {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "catalog");

        String message = "";
        String errorMessage = "";
        Boolean descFound = false;

        // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
//        if (shoppingCart == null) synchronized (this) {
//            if (shoppingCart == null) {
//                shoppingCart = WebObjectFactory.getNewShoppingCart();
//                session.setAttribute("shoppingCart", shoppingCart);
//            }
//        }
        if (action == null) {
            // do nothing but show page
        } else if ("addItemToCatalog".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemByName(itemName);
            Double fianlItemPrice = Double.parseDouble(itemPrice);
            Integer finalItemQuantity = Integer.parseInt(itemQuantity);
            if (shoppingItem == null) {
                shoppingService.addItemToCatalog(new ShoppingItem(itemName, finalItemQuantity, fianlItemPrice));
                message = "Added " + itemName + " to catalog";
            } else {
                message = itemName + " already in catalog ";
            }
        } else if ("removeItemFromCatalog".equals(action)) {
            shoppingService.removeItemByName(itemName);
            message = "removed " + itemName + " from catalog";
        } else if ("updateItem".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
            Double finalItemPrice = Double.parseDouble(itemPrice);
            Integer finalItemQuantity = Integer.parseInt(itemQuantity);
            shoppingItem.setName(itemName);
            shoppingItem.setQuantity(finalItemQuantity);
            shoppingItem.setPrice(finalItemPrice);
            if (shoppingItem == null) {
                message = itemName + " does not exist to update";
            } else {
                shoppingService.updateItemById(shoppingItem);
                message = "Updated item with ID " + itemId;
            }
            
        } else if ("uploadImage".equals(action)) {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Image dbImage = new Image();
                dbImage.setName(file.getOriginalFilename());
                dbImage.setContent(bytes);
                imageDbRepository.save(dbImage);
                
                ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
                if (shoppingItem != null) {
                    for (ShoppingItemDescription desc: shoppingDescription.getItemDescriptions()){
                        if (shoppingItem.getName().equals(desc.getName())) {
                            desc.setImage(dbImage.getId());
                            shoppingDescription.updateItemDescription(desc);
                            descFound = true;
                        }
                    }
                    if (descFound == false){
                    ShoppingItemDescription desc = new ShoppingItemDescription();
                    desc.setImage(dbImage.getId());
                    desc.setName(shoppingItem.getName());
                    shoppingDescription.addItemDescription(desc);
                    }
                }
                
                message = "Image Uploaded";
            }
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        
        List<ShoppingItemDescription> shoppingItemDescriptions = shoppingDescription.getItemDescriptions();

        Double shoppingcartTotal = shoppingCart.getTotal();
        
        List<Image> image = shoppingDescription.getImages();

        // populate model with values
        model.addAttribute("images", image);

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingItemDescriptions", shoppingItemDescriptions);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "catalog";
    }
    

    @RequestMapping(value = "/cart", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCart(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "cart");

        String message = "";
        String errorMessage = "";

        if (action == null) {
            // do nothing but show page
        } else if ("addItemToCart".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemByName(itemName);
            if (shoppingItem == null) {
                message = "cannot add unknown " + itemName + " to cart";
            } else {
                message = "adding " + itemName + " to cart price= " + shoppingItem.getPrice();
                shoppingCart.addItemToCart(shoppingItem);
            }
        } else if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUuid);
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "cart";
    }
    
    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String aboutCart(Model model, HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);
        
        // used to set tab selected
        model.addAttribute("selectedPage", "about");
        return "about";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contactCart(Model model, HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);
        
        // used to set tab selected
        model.addAttribute("selectedPage", "contact");
        return "contact";
    }


    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for sessionUser
    }

}
