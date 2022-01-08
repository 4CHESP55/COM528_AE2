# System Model

## System Components

Within this project there are five (5) modules:
- bank client 
  - Creates message in a format the bank accepts
  - Posts message to the bank
  - Handles the resulting message from the bank
- dao
  - Defines JPA repositories based on classes in the model
  - Defines sql quieries to perform CRUD actions
- model
  - Contains objects for the complete application
  - Contains interfaces defining functions which use the objects
- service
  - Implements the interfaces from the model, overwriting the functions
  - Populates the database on start up
- web
  - Contains the jsp pages.
  - Contains all css and JQuery/Javascript
  - Uses java classes (controllers) to map requests to the jsp pages and display content based on the result of function used that are defined in the service module


## Models
### UML model of the Model module

![alt text](../documents/images/model.png "Figure model.png" )

There are three services
- Orders
- ShoppingService
- ShoppingCart

These services are used to add items to the shopping cart and also the invoice, which in turn is saved in the database.

The diagram shows the DTO classes that link to them. With some having multiple dependancies on other classes. 

Countries and Dates don't have any relationship to the classes in this model. Instead that are used directly by the web module to for drop down lists. This has been done to reduce the duplicated and long code within the jsp pages.


### UML model of Service module
This diagram shows the implementation and factory classes

![alt text](../documents/images/service.png "Figure model+service.png" )


### UML model of Web Module
This diagram shows the controllers, along with the web object factory and properties dao. The web object factory links to the services classes from the service module.

![alt text](../documents/images/web.png "Figure web.png" )

### UML model of DAO module
This diagram shows the classes within the DAO module

![alt text](../documents/images/dao.png "Figure dao.png" )

Putting all of these on one diagram would result in a clustered and difficult to read diagram, which is why they have been show seperately. However hopefully you can see how they all link to each other.
