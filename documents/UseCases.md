# Use Cases and Feature list

## Diagram

Below is the use case diagram for the shopping application.

![alt text](../documents/drawio/cart_usecase-draw_io.png "Figure cart_usecase-draw_io.png")



## Feature list
1. Catalog
    1. Add item to catalog
    2. Modify item in catalog
        1. Enable/disable item
        2. Set item stock levels
        3. Change item image
        4. modify item details
    3. Delete item from catalog
2. Accounts
    1. Create Account
        1. Shop owner create any account
        2. Customer creates account
    2. Modify account
        1. Shop owner enable/disable accounts
        2. Shop owner modify account role
        3. Customer modify account details
    3. Delete account
    4. Login to account
    5. Log out of account
3. Shopping Cart
    1. Add item to cart
    2. Modify item quanitity in cart
    3. Remove item from cart
4. Orders
    1. View order
    2. View all orders
        1. Customer views all their orders
        2. Shop owner views all orders
    3. Modify order status
5. Modify payment settings
6. Purchase items


## Use Cases
### Catalog

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.1
| **Feature Name**       | Add item to catalog
| **Description:**       | items should be able to be added to the catalog   
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | New item added to catalog
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "New item" button.<br> Fills in item details.<br> Clicks "save".

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.2.1
| **Feature Name**       | Enable/Disable item
| **Description:**       | items should be able to be enabled and disabled so they do not show up in the shop  
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | Item Enabled/Disabled
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "Edit item" button on the item they want to enable/disable.<br> Ticks the box to Enable/Disable item.<br> Clicks "save".

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.2.2
| **Feature Name**       | Set item stock levels
| **Description:**       | item stock levels should be able to be modified 
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | Item stock levels updated
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "Edit item" button on the item they want to change the stock levels.<br> Enters quantity in the quantity field on the form.<br> Clicks "save".

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.2.3
| **Feature Name**       | Change item image
| **Description:**       | item images should be changeable
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | Item image updated
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "Edit item" button on the item they want to change the image.<br> clicks button to choose file under the image section of the form.<br> inspects preview is correct. <br> Clicks "upload".

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.2.4
| **Feature Name**       | Modify item details
| **Description:**       | item details should be changeable
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | Item details updated
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "Edit item" button on the item they want to modify.<br> inputs different details for name/description/price.<br> Clicks "save".

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.3
| **Feature Name**       | Delete item from catalog
| **Description:**       | items should be able to be deleted
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop Owner (admin) logged in
| **Postconditions:**    | Item deleted
| **Flow:**              | Shop Owner navigates to "Manage catalog" page. <br> Clicks on "delete item" button on the item they want to delete.

### Accounts

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 1.1
| **Feature Name**       | Shop owner create any acocunt
| **Description:**       | Shop owner should be able to create any type of account
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in
| **Postconditions:**    | New account created
| **Flow:**              | Shop owner navigates to "Manage users". <br> Clicks on button to add account. <br> Fills out user details. <br> clicks to save user.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.1.2
| **Feature Name**       | Customer creates account
| **Description:**       | A customer should be able to create an account
| **Actors:**            | Customer
| **Preconditions:**     | Customer is not logged in
| **Postconditions:**    | Customer is logged into new account
| **Flow:**              | Customer clicks on link to register. <br> Fills in username and password. <br> Customer is now logged in on the page to add more details.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.2.1
| **Feature Name**       | Shop owner enable/disable accounts
| **Description:**       | The Shop owner should be able to enable and disable accounts
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in 
| **Postconditions:**    | User account disabled
| **Flow:**              | Shop owner navigates to "Manage users". <br> Clicks "modify user" on user to disable. <br> changes item in form for status from enabled to disabled. <br> Clicks save to submit form.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.2.2
| **Feature Name**       | Shop owner modify account role
| **Description:**       | The Shop owner should be able to modify any accounts role
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in
| **Postconditions:**    | User account role changed
| **Flow:**              | Shop owner navigates to "Manage users". <br> Clicks "modify user" on user to change role. <br> changes item in form for role. <br> Clicks save to submit form.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.2.3
| **Feature Name**       | Customer modify account details
| **Description:**       | A Customer should be able to modify account details
| **Actors:**            | Customer
| **Preconditions:**     | Customer logged in
| **Postconditions:**    | Customer details changed
| **Flow:**              | Customer navigates to "Profile". <br> Clicks through form changing any fields. <br> Clicks update to save new details.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.3
| **Feature Name**       | Delete account
| **Description:**       | The Shop owner should be able to delete any account
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in
| **Postconditions:**    | Account deleted
| **Flow:**              | Shop owner navigates to "manage users". <br> Clicks "delete user" on user to delete.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.4
| **Feature Name**       | Login to account
| **Description:**       | Accounts should be able to be logged in to
| **Actors:**            | Shop Owner, <br> Customer
| **Preconditions:**     | Account logged out
| **Postconditions:**    | Account logged in
| **Flow:**              | Navigate to login. <br> Enter username and password.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 2.5
| **Feature Name**       | Log out of account
| **Description:**       | Account logged in
| **Actors:**            | Shop Owner, <br> Customer
| **Preconditions:**     | Account logged in
| **Postconditions:**    | Account logged out
| **Flow:**              | Navigate to logout. <br> Account logged out.

### Shopping Cart

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 3.1
| **Feature Name**       | Add item to cart
| **Description:**       | Items should be able to be added to the shopping cart
| **Actors:**            | Customer
| **Preconditions:**     | Customer logged in 
| **Postconditions:**    | Item in cart
| **Flow:**              | Customer navigated to shop. <br> Customer clicks "add to cart" on any item. <br> Cart is updated with item.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 3.2
| **Feature Name**       | Modify item quantity in cart
| **Description:**       | Items in the cart should be able to be reduced or increased in quantity
| **Actors:**            | Customer
| **Preconditions:**     | Customer logged in with items in cart
| **Postconditions:**    | Cart items quantity updated
| **Flow:**              | Customer navigates to "Cart". <br> Clicks + or - buttons next to item in cart. <br> Cart updates.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 3.3
| **Feature Name**       | Remove item from cart
| **Description:**       | Items in cart should be removeable
| **Actors:**            | Customer
| **Preconditions:**     | Customer logged in with items in cart
| **Postconditions:**    | Item no longer in cart
| **Flow:**              | Customer navigates to "Cart". <br> Clicks button to remove item from cart. <br> Item is removed and cart is updated.

### Orders

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 4.1
| **Feature Name**       | View order
| **Description:**       | Customers should be able to view their order
| **Actors:**            | Customer
| **Preconditions:**     | Customer is logged in and has already made a purchase
| **Postconditions:**    | Customer can see the order details
| **Flow:**              | Customer navigates to "My Orders". <br> Clicks on order to view. <br> Order is displayed.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 4.2.1
| **Feature Name**       | Customer views all their orders
| **Description:**       | Customers should be able to view their order history
| **Actors:**            | Customer
| **Preconditions:**     | Customer is logged in and has already made a purchase
| **Postconditions:**    | Customer can see all previous orders
| **Flow:**              | Customer navigates to "My Orders". <br> All orders are shown.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 4.2.2
| **Feature Name**       | Shop owner views all orders
| **Description:**       | The Shop owner should be able to view all orders
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in, customers have made purchases
| **Postconditions:**    | Shop owner can see all orders
| **Flow:**              | Shop owner navigates to "Manage Orders". <br> All orders are shown.

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 4.3
| **Feature Name**       | Modify order status
| **Description:**       | The Shop owner should be able to modify the order status
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in, customers have made purchases
| **Postconditions:**    | Customer order status updated
| **Flow:**              | Shop owner navigates to "Manage Orders". <br> Clicks on "Modify order" on the order to modify. <br> Uses the drop down to change the status. <br> Clicks update.

## Modify Payment Settings

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 5
| **Feature Name**       | Modify payment settings
| **Description:**       | The Shop owner should be able to modify payment settings for the application
| **Actors:**            | Shop Owner
| **Preconditions:**     | Shop owner logged in
| **Postconditions:**    | payment settings updated
| **Flow:**              | Shop owner navigates to "Payment Settings". <br> Payment settings are displayed in a form. <br> Changes values in form. <br> Clicks update button to submit the changes.

## Purchase items

|                        |      |
| :---                   | :--- |
| **Feature No.**        | 6
| **Feature Name**       | Purchase items
| **Description:**       | The customer should be able to purchase the items in the basket
| **Actors:**            | Customer
| **Preconditions:**     | Customer logged in with items in basket
| **Postconditions:**    | Order placed
| **Flow:**              | Customer navigates to "Cart". <br> Clicks "checkout". <br> Fills in payment details. <br> Clicks "Place Order". <br> Order is placed and invoice is shown.