package com.jpmorgan.stocks.simple.model;

import org.apache.log4j.Logger;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * 
 * @author Mennea Giuseppe
 * 
 */
@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	@OneToOne
	private Trade trade;

	/**
	 * 
	 */
	private String stockSymbol = null;

	/**
	 * 
	 */
	private StockType stockType = StockType.COMMON;

	/**
	 * 
	 */
	private double lastDividend = 0.0;

	/**
	 * 
	 */
	private double fixedDividend = 0.0;

	/**
	 * 
	 */
	private double parValue = 0.0;

	/**
	 * 
	 */
	private double tickerPrice = 0.0; 


	/**
	 * 
	 * @return
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * 
	 * @param stockSymbol
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * 
	 * @return
	 */
	public StockType getStockType() {
		return stockType;
	}

	/**
	 * 
	 * @param stockType
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	/**
	 * 
	 * @return
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/**
	 * 
	 * @param lastDividend
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * 
	 * @return
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * 
	 * @param fixedDividend
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * 
	 * @return
	 */
	public double getParValue() {
		return parValue;
	}

	/**
	 * 
	 * @param parValue
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/**
	 * 
	 * @return
	 */
	public double getDividendYield() {
		double dividendYield = -1.0;
		if(tickerPrice > 0.0){
			if( stockType==StockType.COMMON){
				dividendYield = lastDividend / tickerPrice;
			}else{
				// PREFERRED
				dividendYield = (fixedDividend * parValue ) / tickerPrice;
			}
		}
		return dividendYield;
	}


	/**
	 * 
	 * @return
	 */
	public double getPeRatio() {
		double peRatio = -1.0;

		if(tickerPrice > 0.0){
			peRatio = tickerPrice / getDividendYield();	
		}

		return peRatio;
	}

	/**
	 * 
	 * @return
	 */
	public double getTickerPrice() {
		return tickerPrice;
	}

	/**
	 * 
	 * @param tickerPrice
	 */
	public void setTickerPrice(double tickerPrice) {
		this.tickerPrice = tickerPrice;

	}


}
