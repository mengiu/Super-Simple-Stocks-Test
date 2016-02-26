package server.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.jpmorgan.stocks.simple.arch.SimpleStockServicesFactory;
import com.jpmorgan.stocks.simple.arch.SpringService;
import com.jpmorgan.stocks.simple.dao.StockDAO;
import com.jpmorgan.stocks.simple.dao.TradeDAO;
import com.jpmorgan.stocks.simple.model.Trade;
import com.jpmorgan.stocks.simple.services.SimpleStockService;

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
