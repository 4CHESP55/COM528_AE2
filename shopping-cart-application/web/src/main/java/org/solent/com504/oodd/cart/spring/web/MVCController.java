package org.solent.com504.oodd.cart.spring.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Image;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.web.WebObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    ShoppingItemCatalogRepository shoppingItemCatalogRepository;
           
    @Autowired
    UserRepository userRepository;

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
    public String viewHome(@RequestParam(name = "action", required = false) String action,
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
        
        int final_buy_quantity = 1;

        if (null == action) {
            // do nothing but show page
        } else // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
//        if (shoppingCart == null) synchronized (this) {
//            if (shoppingCart == null) {
//                shoppingCart = WebObjectFactory.getNewShoppingCart();
//                session.setAttribute("shoppingCart", shoppingCart);
//            }
//        }
        switch (action) {
            case "addItemToCart":
                ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
                if (shoppingItem == null) {
                    message = "cannot add unknown " + itemName + " to cart";
                } else {
                    message = "adding " + itemName + " to cart price= " + shoppingItem.getPrice();
                    shoppingCart.addItemToCart(shoppingItem, final_buy_quantity);
                }   break;
            case "removeItemFromCart":
                message = "removed " + itemName + " from cart";
                shoppingCart.removeItemFromCart(itemUuid);
                break;
            default:
                message = "unknown action=" + action;
                break;
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

        return "home";
    }
    
    @RequestMapping(value = "/shop", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewShop(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            @RequestParam(name = "buy_quantity", required = false) String buy_quantity,
            @RequestParam(name = "page", required = false) String page,
            @RequestParam(name = "search", required = false) String search,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "shop");

        String message = "";
        String errorMessage = "";
        
        int final_buy_quantity = 1;
        if(buy_quantity != null){
            final_buy_quantity = Integer.parseInt(buy_quantity);
        }     

        int intPage = 0;
        int recordsPerPage = 2;
        if(page == null) {
            intPage = 1;
        } else {
            intPage = Integer.parseInt(page);
        }
        List<ShoppingItem> allAvailableItems = shoppingService.getAvailableItems();
        if(search != null){
            allAvailableItems = shoppingService.getSearchedItems(search);
        }
        int noOfRecords = allAvailableItems.size();
        List<ShoppingItem> availableItemsOnPage = allAvailableItems.subList((intPage-1)*recordsPerPage, ((intPage-1)*recordsPerPage+recordsPerPage > noOfRecords ? noOfRecords : (intPage-1)*recordsPerPage+recordsPerPage));
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);


        if (null == action) {
            // do nothing but show page
        } else switch (action) {
            case "addItemToCart":
                ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
                if (shoppingItem == null) {
                    message = "cannot add unknown " + itemName + " to cart";
                } else {
                    message = "adding " + itemName + " to cart price= " + shoppingItem.getPrice();
                    shoppingCart.addItemToCart(shoppingItem, final_buy_quantity);
                }   break;
            default:
                message = "unknown action=" + action;
                break;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();
        
        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        
        Double shoppingcartTotal = shoppingCart.getTotal();
        
        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("availableItemsOnPage", availableItemsOnPage);
        model.addAttribute("currentPage", intPage);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "shop";
    }
    
    @RequestMapping(value = "/product", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewProduct(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemId", required = false) Long itemId,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "product");

        String message = "";
        String itemName = "";
        Double itemPrice = 0.00;
        String itemDescription = "";
        String itemImage = "";
        Integer itemQuantity = 0;
        if (null == action) {
            // do nothing but show page
        } else switch (action) {
            case "viewProduct":
                ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
                if (shoppingItem == null) {
                    message = "cannot find item with id:  " + itemId;
                } else {
                    itemName = shoppingItem.getName();
                    itemPrice = shoppingItem.getPrice();
                    itemQuantity = shoppingItem.getQuantity();
                    itemDescription = shoppingItem.getDescription();
                    if (shoppingItem.getImage() != null){
                        itemImage = shoppingItem.getImage().getBase64image();
                    }
                }   break;
            default:
                message = "unknown action=" + action;
                break;
        }

        
        // populate model with values
        model.addAttribute("message", message);
        model.addAttribute("itemId", itemId);
        model.addAttribute("itemName", itemName);
        model.addAttribute("itemPrice", itemPrice);
        model.addAttribute("itemDescription", itemDescription);
        model.addAttribute("itemImage", itemImage);
        model.addAttribute("itemQuantity", itemQuantity);

        return "product";
    }
    
    @RequestMapping(value = "/catalog", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCatalog(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name= "file", required = false) MultipartFile file,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "itemDesc", required = false) String itemDesc,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemPrice", required = false) String itemPrice,
            @RequestParam(name = "itemQuantity", required = false) String itemQuantity,
            @RequestParam(name = "enabled", required = false) String enabled,
            Model model,
            HttpSession session) throws IOException {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);
        
        
        // used to set tab selected
        model.addAttribute("selectedPage", "catalog");

        String message = "";
        String errorMessage = "";

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            return "redirect:home";
        }
        
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
            Boolean finalEnabled = Boolean.parseBoolean(enabled);
            Image image = new Image();
            
            if (shoppingItem == null) {
                ShoppingItem newItem = new ShoppingItem(itemName, finalItemQuantity, fianlItemPrice, image, itemDesc, finalEnabled);
                shoppingService.addItemToCatalog(newItem);
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
            Boolean finalEnabled = Boolean.parseBoolean(enabled);
            
            if (shoppingItem == null) {
                message = itemName + " does not exist to update";
            } else {
                shoppingItem.setName(itemName);
                shoppingItem.setQuantity(finalItemQuantity);
                shoppingItem.setPrice(finalItemPrice);
                shoppingItem.setDescription(itemDesc);
                shoppingItem.setEnabled(finalEnabled);
                shoppingItemCatalogRepository.save(shoppingItem);
                message = "Updated item with ID " + itemId;
            }
            
        } else if ("uploadImage".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemById(itemId);
            shoppingItem.setImage(shoppingService.saveImage(file));
            shoppingItemCatalogRepository.save(shoppingItem);

            message = "Image Uploaded";
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> allItems = shoppingService.getAllItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        
        Double shoppingcartTotal = shoppingCart.getTotal();
        

        // populate model with values

        // populate model with values
        model.addAttribute("allItems", allItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
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
        } else if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUuid);
        } else if ("reduceItemFromCart".equals(action)) {
            message = "reducing  "+itemName + " in cart";
            shoppingCart.reduceItemFromCart(itemUuid);
        } else if ("increaseItemFromCart".equals(action)) {
            message = "increasing "+itemName + " in cart";
            shoppingCart.increaseItemFromCart(itemUuid);
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
    
    @RequestMapping(value = "/checkout", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCheckout(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            @ModelAttribute("errorMessage") String redirectError,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "cart");

        String message = "";
        String errorMessage = "";
        if (redirectError != null){
            errorMessage = redirectError;
        }

        User checkoutUser = null;
        if (!UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
            List<User> userList = userRepository.findByUsername(sessionUser.getUsername());
            checkoutUser = userList.get(0);
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();
        

        model.addAttribute("checkoutUser", checkoutUser);

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "checkout";
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
