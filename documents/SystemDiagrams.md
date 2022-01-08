# System Diagrams
## Robustness Diagram

![alt text](../documents/drawio/cart-robustness-drawio.png "Figure cart-robustness-drawio.png")


## Sequence Diagrams
Below are some sequence diagrams, detailing the sequence of how each page works with their controllers.

### Placing an Order

![alt text](../documents/images/OrderController_viewPlaceOrder.png "Figure OrderController_viewPlaceOrder.png")
This diagram is rather large due to the steps involved in placing an order.

Not only is an order placed, but the item quantity is reduced from stock, the cart is cleared, an invoice of the order is created and the transaction with the bank takes place.

### Viewing Orders

![alt text](../documents/images/OrderController_viewOrders.png "Figure OrderController_viewOrders.png")
This diagram contains a duplicated sequence. However this is due to the post parameters. getOrderByInvoiceNumber is called for when viewing an order and for when updating an order.

### Updating Payment Settings
![alt text](../documents/images/PaymentSettingsController_viewPaymentSettings.png "Figure PaymentSettingsController_viewPaymentSettings.png")
This sequence is when updating the payment settings for the account to send the payment to.

### View Cart
![alt text](../documents/images/MVCController_viewCart.png "Figure MVCController_viewCart.png")
In the cart there are options to increase, decrease or remove items.

### Catalog
![alt text](../documents/images/MVCController_viewCatalog.png "Figure MVCController_viewCatalog.png")
The Catalog page allows admins to modify items and also update them. Due to the way JPA works, saving an item with the same ID will update the database, and so there doesn't need to be any complex update sql commands.

### View Checkout
![alt text](../documents/images/MVCController_viewCheckout.png "Figure MVCController_viewCheckout.png")
The sequence diagram doesnt show any items in the viewcheckout method, however this is because the shopping cart is stored in the session.

### Shop
![alt text](../documents/images/MVCController_viewShop.png "Figure MVCController_viewShop.png")
The shop view allows items to be added to the cart.

### View Product
![alt text](../documents/images/MVCController_viewProduct.png "Figure MVCController_viewProduct.png")
Items can be added to cart from the product view, however the jsp posts the request to the shop page to achieve this.