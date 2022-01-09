# Test Plan
This test plan provides a set of steps to test the use cases against the delivered solution.


## Test Case Mapping
For full details of the test cases see [Use Cases](UseCases.md).

| Test Case | Use Case                           |  \|   | Test Case | Use Case                           | 
| :---      | :---                               | :---  |:---       | :---                               |
|   TC01    | Login to account                   |  \|   |   TC13    | Enable/disable item                | 
|   TC02    | Modify payment settings            |  \|   |   TC14    | Set item stock levels              |
|   TC03    | Log out of account                 |  \|   |   TC15    | Change item image                  |  
|   TC04    | Customer creates account           |  \|   |   TC16    | modify item details                |  
|   TC05    | Customer modify account details    |  \|   |   TC17    | Delete item from catalog           |  
|   TC06    | Add item to cart                   |  \|   |   TC18    | Shop owner create any account      |  
|   TC07    | Modify item quanitity in cart      |  \|   |   TC19    | Shop owner modify account role     | 
|   TC08    | Remove item from cart              |  \|   |   TC20    | Shop owner enable/disable accounts | 
|   TC09    | Purchase items                     |  \|   |   TC21    | Delete account                     |  
|   TC10    | Customer views all their orders    |  \|   |   TC22    | Shop owner views all orders        |  
|   TC11    | View order                         |  \|   |   TC23    | Modify order status                | 
|   TC12    | Add item to catalog                |  \|   | 


## Test Steps
Below are the full steps to test all of the use cases.

**Prerequisite:** Application is up and running and tester has navigated to http://localhost:8080/shoppingCartApplication/home in the browser.

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Input</th>
            <th>Expected Result</th>
            <th>Pass/Fail</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="5">Test Case: TC01 - Login to Account</td>
        </tr>
        <tr>
            <td>1</td>
            <td>Click on "Login or create a new Account" in the navigation bar.</td>
            <td>Redirected to the login page</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>2</td>
            <td>Enter the username and password for the global admin and click "Login"</td>
            <td>Logged in and redirected back to home page.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC02 - Modify payment settings</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Click on "Admin" in the navigation bar.</td>
            <td>Drop down appears with multiple options.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>4</td>
            <td>Click on "Payment Settings".</td>
            <td>Payment settings page loaded.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>5</td>
            <td>Change any field on the page and click "Update Payment Settings".</td>
            <td>Payment settings updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC03 - Log out of account</td>
        </tr>
        <tr>
            <td>6</td>
            <td>Click on "Admin" in the navigation bar and then "logout".</td>
            <td>Account is logged out and redirected to the home page.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC04 - Customer creates account</td>
        </tr>
        <tr>
            <td>7</td>
            <td>Click on "Login or create a new Account" in the navigation bar.</td>
            <td>Redirected to the login page.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>8</td>
            <td>Click on "Create New Account".</td>
            <td>Page displayed to enter a username and confirm password.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>9</td>
            <td>Enter a username and password.</td>
            <td>Redirected to enter more details.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC05 - Customer modify account details</td>
        </tr>
        <tr>
            <td>10</td>
            <td>Click on "Address" on the left and enter a house number.</td>
            <td>Page changes to show address options, input is accepted.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>11</td>
            <td>Click "Save Changes".</td>
            <td>Address updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC06 - Add item to cart</td>
        </tr>
        <tr>
            <td>12</td>
            <td>Click on "Shop" in the navigation bar.</td>
            <td>Redirected to the shop page.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>13</td>
            <td>Click "Add item" on any item.</td>
            <td>prompt syaing item added to cart.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC07 - Modify item quanitity in cart</td>
        </tr>
        <tr>
            <td>14</td>
            <td>Click "Cart" in the navigation bar.</td>
            <td>The shopping cart is displayed</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>15</td>
            <td>Click the plus sign next to the item.</td>
            <td>Quanitity of item in cart increases.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC08 - Remove item from cart</td>
        </tr>
        <tr>
            <td>16</td>
            <td>Click on the "Remove button".</td>
            <td>Item removed from cart.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC09 - Purchase items</td>
        </tr>
        <tr>
            <td>17</td>
            <td>Repeat step 12-14.</td>
            <td></td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>18</td>
            <td>Click "Checkout".</td>
            <td>Redirected to checkout page.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>19</td>
            <td>Click "Conintue to payment".</td>
            <td>form to enter card details appears.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>20</td>
            <td>Enter card details and click "Place Order".</td>
            <td>Order is accepted with confirmation on screen.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC10 - Customer views all their orders</td>
        </tr>
        <tr>
            <td>21</td>
            <td>Click on your username then "My Orders" in the navigation bar.</td>
            <td>Page loads to show list of orders.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC11 - View order</td>
        </tr>
        <tr>
            <td>22</td>
            <td>Click "View Order".</td>
            <td>Order details are brought up.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC12 - Add item to catalog</td>
        </tr>
        <tr>
            <td>23</td>
            <td>Logout form the user account and login to the admin account.</td>
            <td>Admin accoutn logged in.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>24</td>
            <td>Click "Manage Catalog" from the drop down after clicking Admin.</td>
            <td>Page loads showing a list of all items.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>25</td>
            <td>Click "New Item".</td>
            <td>Drop down appears to enter item details.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>26</td>
            <td>Fill in details and click "Add Item".</td>
            <td>Item added.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC13 - Enable/disable item</td>
        </tr>
        <tr>
            <td>27</td>
            <td>Click "Edit" on an item.</td>
            <td>Item details displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>28</td>
            <td>Click on "Disable" and click "update item".</td>
            <td>Item updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC14 - Set item stock levels</td>
        </tr>
        <tr>
            <td>29</td>
            <td>Click "Edit" on an item.</td>
            <td>Item details displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>30</td>
            <td>Enter different quantity and click "Update Item".</td>
            <td>item updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC15 - Change item image</td>
        </tr>
        <tr>
            <td>31</td>
            <td>Click "Edit" on an item.</td>
            <td>Item details displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>32</td>
            <td>Click "Edit image" then "browse image".</td>
            <td>Image preview is displayed</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>33</td>
            <td>Click "Upload".</td>
            <td>Image is uploaded and item updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC16 - modify item details</td>
        </tr>
        <tr>
            <td>34</td>
            <td>Click "Edit" on item.</td>
            <td>item details shown.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>35</td>
            <td>Change text in description and click "Update item".</td>
            <td>Item updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC17 - Delete item from catalog</td>
        </tr>
        <tr>
            <td>36</td>
            <td>Click "Delete" next to item.</td>
            <td>Item deleted.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC18 - Shop owner create any account</td>
        </tr>
        <tr>
            <td>37</td>
            <td>Click "Admin" then "Manage users"</td>
            <td>List of users diaplyed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>38</td>
            <td>Click "Add user".</td>
            <td>Registration page displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>39</td>
            <td>Enter username and password.</td>
            <td>User created and page to add more details loads.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC19 - Shop owner modify account role</td>
        </tr>
        <tr>
            <td>40</td>
            <td>Click "return to users".</td>
            <td>List of users displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>41</td>
            <td>Click "Modify User" next to a user.</td>
            <td>User details are displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>42</td>
            <td>Click on drop down to change role to administrator and click "Save changes".</td>
            <td>Changes are saved.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC20 - Shop owner enable/disable accounts</td>
        </tr>
        <tr>
            <td>43</td>
            <td>Click "Disabled" in the drop down and "Save Changes".</td>
            <td>Changes are saved.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC21 - Delete account</td>
        </tr>
        <tr>
            <td>44</td>
            <td>Click "Return to Users".</td>
            <td>List of users displayed</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>45</td>
            <td>Click "Delete User" next to user.</td>
            <td>User is deleted.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC22 - Shop owner views all orders</td>
        </tr>
        <tr>
            <td>46</td>
            <td>Click "Admin" > "Manage Orders".</td>
            <td>List of all orders displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5">Test Case: TC23 - Modify order status</td>
        </tr>
        <tr>
            <td>47</td>
            <td>Click "Edit Order".</td>
            <td>Srop down for order status is displayed.</td>
            <td>PASS</td>
            <td></td>
        </tr>
        <tr>
            <td>48</td>
            <td>Select "PROCESSING" and click update status.</td>
            <td>status is updated.</td>
            <td>PASS</td>
            <td></td>
        </tr>
    </tbody>
</table>
