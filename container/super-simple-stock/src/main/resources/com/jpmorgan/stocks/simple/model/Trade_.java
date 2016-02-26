package com.jpmorgan.stocks.simple.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-24T17:56:11.638+0100")
@StaticMetamodel(Trade.class)
public class Trade_ {
	public static volatile SingularAttribute<Trade, Date> timeStamp;
	public static volatile SingularAttribute<Trade, TradeIndicator> tradeIndicator;
	public static volatile SingularAttribute<Trade, Integer> sharesQuantity;
	public static volatile SingularAttribute<Trade, Double> price;
	public static volatile SingularAttribute<Trade, Long> id;
}
