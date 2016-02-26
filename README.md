# Super Simple Stocks Test
 Super simple stocks Test is an application to manage trades on a set of stocks and it's a technical test as part of 
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
 
 I used for this project JPA ( implemented by HiperLink ) to implement the persistence layer. 
 I used H2 database engine so all data are held in memory ( responding to one of the constraints ).
 I used Jetty as HTTP server, HTTP client, and javax.servlet container.
 Jetty is an open-source project providing an HTTP server, HTTP client, and javax.servlet container.

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

 The Maven project is composed by a father maven project ( named container ) and 2 modules maven named
 super-simple-stock and web-app.
 The easiest way to run the sample is using maven command mvn package jetty:run.
 
 Spring give a powerful pattern as Inversion of Control, which allows us to have a cleaner code, 
 aspect oriented programming AoP, etc.
 
 Although the approach is SOA, the implementation of the service is built as _**java library**_ as
 a jar artifact named _**web-app-0.0.1-SNAPSHOT.jar**_. Because there are no explicit 
 integration requirements, the simplest decision was to create a java library, which could be 
 integrated in all the JAVA technologies. 
 
 The java library for this assignment is the more flexible solution with the current requirements. 
 I published the service in the library as a _**RESTful Web Service**_. 
 
 To build a RESTful web service I used Apache CFX framework.
 Apache CXF is an open source services framework. CXF helps you build and develop services using frontend programming APIs, 
 like JAX-WS and JAX-RS. These services can speak a variety of protocols such as SOAP, XML/HTTP, RESTful HTTP, or 
 CORBA and work over a variety of transports such as HTTP, JMS or JBI.
 The name of the class implementing the REST Service is SimpleStockJSONService

```java
@Produces({"application/xml","application/json"})
@Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
public class SimpleStockJSONService {
	Logger logger = Logger.getLogger(SimpleStockJSONService.class);

	public void init() {
		TradeDAO tradeDAO;
		
		logger.info("Start  recordATradeTest ...");

		// Create the stock service and verify it's not null object
		SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();
		Assert.assertNotNull(simpleStockService);

		// Recover the trades configured in spring for the unit test
		@SuppressWarnings("unchecked")
		ArrayList<Trade> tradeList = SpringService.INSTANCE.getBean("tradeList", ArrayList.class);
		Assert.assertNotNull(tradeList);
		logger.info("Trade List size: "+tradeList.size());


		try{
			// Initial trades are empty, means, trades number equls to cero (0)
			tradeDAO = SpringService.INSTANCE.getBean("tradeDAO", TradeDAO.class);
			int tradesNumber = tradeDAO.listTrade().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, 0);

			// Insert many trades in the stock system
			for(Trade trade: tradeList){
				
				boolean result = simpleStockService.recordTrade(trade);
				Assert.assertTrue(result);
			}
			
			tradesNumber = tradeDAO.listTrade().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, tradeList.size());
			StockDAO stockDAO = SpringService.INSTANCE.getBean("stockDAO", StockDAO.class);
			logger.info("Stock number: "+stockDAO.listStock().size());


		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("Finish recordATradeTest ...OK");
		
	}
	
	
	@POST
	@Path("/calculatedividendyield/{stock}/")
    public Double calculateDividendYield(@PathParam("stock") String stockSymbol) throws Exception{
        // Get the simple stcok service from the factory
        SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();

        // Delegates the work to the java library
        return simpleStockService.calculateDividendYield(stockSymbol);
    }

	@POST
	@Path("/calculatePERatio/{stock}/")
    public Double calculatePERatio(@PathParam("stock") String stockSymbol) throws Exception{
        // Get the simple stcok service from the factory
        SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();

        // Delegates the work to the java library
        return simpleStockService.calculatePERatio(stockSymbol);
    }
	
	@POST
	@Path("/calculateStockPrice/{stock}/")
    public Double calculateStockPrice(@PathParam("stock") String stockSymbol) throws Exception{
        // Get the simple stcok service from the factory
        SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();

        // Delegates the work to the java library
        return simpleStockService.calculateStockPrice(stockSymbol);
    }
	
	@POST
	@Path("/calculateGBCEAllShareIndex")
    public Double calculateGBCEAllShareIndex() throws Exception{
        // Get the simple stcok service from the factory
        SimpleStockService simpleStockService = SimpleStockServicesFactory.INSTANCE.getSimpleStockService();

        // Delegates the work to the java library
        return simpleStockService.calculateGBCEAllShareIndex();
    }

}

```
 Under you can see the response to the call at the method REST calculateGBCEAllShareIndex using client REST "Chrome Poser"
 
 
 ![Super Simple Stocks Test - Rest Method
 Calling](https://github.com/mengiu/Super-Simple-Stocks-Test/blob/master/container/super-simple-stock/src/main/resources/images/testCallingMethodREST.png "Super Simple Stocks Test - Rest Method Calling")
