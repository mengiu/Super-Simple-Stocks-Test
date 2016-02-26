![Super Simple Stocks - Technical Design Modeling](https://github.com/jainebri/Super-Simple-Stocks/blob/master/super-simple-stock/src/main/resources/images/super-simple-stocks-model.png "Super Simple Stocks - Technical Design Modeling")
  
 -For this technical test, the factory component just has one method _**getSimpleStockService**_, that creates a singleton instance of the **SimpleStockService**, which is the main service in the app and contains all method for the calculations. The class SimpleStocksServicesFactoryImpl is the implementation of the factory and implements a thread safe singleton pattern proposed by Bill Puigh. The next snippet of code ilustrates how to use the factory to create a service:
 +For this technical test, the factory component just has one method _**getSimpleStockService**_, that creates a singleton instance of the **SimpleStockService**, which is the main service in the app and contains all method for the calculations. The class SimpleStocksServicesFactoryImpl is the implementation of the factory and implements a thread safe singleton pattern proposed by Bill Pugh. The next snippet of code ilustrates how to use the factory to create a service:
  
  ```java
  SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();
 @@ -134,7 +134,7 @@ SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getS
  
  As all services are configured in the Spring framework, there are many possibilities to design and build the structure of the services, but for this application we have defined that the border services only can use the services in the backend layer. So, The service _**StocksEntityManager**_, is injected by IoC into the border service SimpleStockService. As one of the constraints of the technical test is 'no database', the entity manager service represents the persistence layer of the application holding all data in memory and providing the methods to recover and store socks and trades in the app. The SimpleStocksService use the entity manager to simulate the database operations for the stocks application.
  
 -Finally, the **SimpleStockServiceImpl** implements all the functionalities coding the bussiness rules to make the calculations of the dividend yield, P/E Ratio, stock price, and GBCE All Share índex.
 +Finally, the **SimpleStockServiceImpl** implements all the functionalities coding the bussiness rules to make the calculations of the dividend yield, P/E Ratio, stock price, and GBCE All Share index.
  
  ##### Unit Test
  
