package com.jpmorgan.stocks.simple.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Mennea Giuseppe
 *
 */
@Entity
public class Trade {
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp = null;
	
	/**
	 * 
	 */
    @OneToOne(mappedBy = "trade", cascade = CascadeType.PERSIST)
	private Stock stock = null;
	
	public Long getId() {
		return id;
	}

	/**
	 * 
	 */
	private TradeIndicator tradeIndicator = TradeIndicator.BUY;
	
	/**
	 * 
	 */
	private int sharesQuantity = 0;
	
	/**
	 * 
	 */
	private double price = 0.0;
	
	
	/**
	 * 
	 */
	public Trade(){
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}
	
	/**
	 * 
	 * @param sharesQuantity
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}
	
	/**
	 * 
	 * @return
	 */
	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}
	
	/**
	 * 
	 * @param tradeIndicator
	 */
	public void setTradeIndicator(TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public Stock getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		String pattern = "Trade Object [timeStamp: %tF %tT, stock: %s, indicator: %s, shares quantity: %7d, price: %8.2f]";
		return String.format(pattern, timeStamp,timeStamp, stock, tradeIndicator, sharesQuantity, price);
	}
	
	
	
}
