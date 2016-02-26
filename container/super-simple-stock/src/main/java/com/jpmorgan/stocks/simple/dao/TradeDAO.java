package com.jpmorgan.stocks.simple.dao;

import java.util.List;

import com.jpmorgan.stocks.simple.model.Trade;

public interface TradeDAO {
	public List<Trade> listTrade();
	public Trade getTrade(Long id);
	public Trade getTrade(String stockSimbol);
	public long addTrade(Trade trade);
}
