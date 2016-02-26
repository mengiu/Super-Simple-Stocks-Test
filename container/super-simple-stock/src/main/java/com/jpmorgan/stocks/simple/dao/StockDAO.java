package com.jpmorgan.stocks.simple.dao;

import java.util.List;

import com.jpmorgan.stocks.simple.model.Stock;

public interface StockDAO {
	public List<Stock> listStock();
	public Stock getStock(Long id);
	public Stock getStock(String stockSimbol);
	public long addStock(Stock Stock);

}
