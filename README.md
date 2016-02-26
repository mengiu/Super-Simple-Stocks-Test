# Super Simple Stocks 
 Super simple stocks is an application to manage trades on a set of stocks and it's a technical test as part of 
 the hiring process for a very important company.
 
 ### 1. Assignment Description
 
 ##### Requirements
 
 1.	Provide working source code that will:
 
     a.	For a given stock:
     
         i.    Calculate the dividend yield.
         ii.   Calculate the P/E Ratio.
         iii.  Record a trade, with timestamp, quantity of shares, buy or sell indicator and price.
         iv.   Calculate Stock Price based on trades recorded in past 15 minutes.
 
     b.	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
 
 ##### Constraints & Notes
 
 1.	Written in one of these languages:
     
     * Java, C#, C++, Python.
     
 2.	No database or GUI is required, all data need only be held in memory.
 
 3.	Formulas and data provided are simplified representations for the purpose of this exercise.
 
 ##### Global Beverage Corporation Exchange
 
 Stock Symbol  | Type | Last Dividend | Fixed Dividend | Par Value
 ------------- | ---- | ------------: | :------------: | --------: 
 TEA           | Common    | 0  |    | 100
 POP           | Common    | 8  |    | 100
 ALE           | Common    | 23 |    | 60
 GIN           | Preferred | 8  | 2% | 100
 JOE           | Common    | 13 |    | 250
 
 
 
 ### 2. Solution
 
 ##### Architecture and Assumptions
 
 In a _**S**ervice **O**riented **A**rchitecture (**SOA**)_, a software application is designed 
 by defining components, which provides services to other components in other applications. Typically, 
 those services are available to be consumed using a network through some specifics communication 
 protocols. By definition a _**service**_ is a self-contained component, which offer a specific 
 functionality and could be designed to perform one or more operations. This make possible reuse 
 code just changing the way the service interoperates with other services.
 
 Following the SOA approach, the solution for the assignment is designed to provide the service 
 _**SimpleStockService**_, which has operations to calculate the dividend yield, P/E Ratio, 
 Stock Price and record trades for a given stock. Besides, the service provides an operation 
 to calculate the GBCE All Share Index for all stocks supported by the Super Simple Stocks application. 
 Providing this service, all the requirements of the assignment are met.
 
 Responding to one of the constraints, the implementation of the solution is written in JAVA 
 language using Apache MAVEN as a software project management tool and Spring Framework. 
 As it is widely known by the JAVA developer community, MAVEN 
 provides some powerful features in software development, someone of them as: 
 A easy and flexible way to build all artifacts in the project, generates quality reports, 
 execution and reporting of unit test, support for continuous integration development, etc.
 
 Spring give a powerful pattern as Inversion of Control, which allows us to have a cleaner code, 
 aspect oriented programming AoP, etc. Although, Spring is used mainly as object container, 
 for example to inject the information of the stocks supported by the application and to configure 
 the data for the unit test, include other features of spring in the application is an straightforward 
 task. Enable the use of annotations, scan spring components in the code and place holder for 
 the properties are already done.
 
 
 Although the approach is SOA, the implementation of the service is built as _**java library**_ as
 a jar artifact named _**super-simple-stock-0.0.1-SNAPSHOT.jar**_. Because there are no explicit 
 integration requirements, the simplest decision was to create a java library, which could be 
 integrated in all the JAVA technologies. We don't know if the requirements are oriented to integrate 
 the functionality within an existing web application just to complete the app, or they are intended to 
 be published as web services to be consumed by external systems or they will be integrated as part of 
 a SOA services choreography in a more complex system.
 
 The java library for this assignment is the more flexible solution with the current requirements. 
 For example, when an integration need shows up, specifying that an external stock market system wants 
 to know the information for the stocks in the Super Simple Stocks app. One option is to publish 
 the service in the library as a _**RESTful Web Service**_. 
 
 The design of the library will allow us to respond to this requirement very fast. To build a RESTful web 
 service using Apache CFX.
 
 
