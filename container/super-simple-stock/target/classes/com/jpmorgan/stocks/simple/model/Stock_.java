package com.jpmorgan.stocks.simple.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-24T18:02:05.956+0100")
@StaticMetamodel(Stock.class)
public class Stock_ {
	public static volatile SingularAttribute<Stock, String> stockSymbol;
	public static volatile SingularAttribute<Stock, StockType> stockType;
	public static volatile SingularAttribute<Stock, Double> lastDividend;
	public static volatile SingularAttribute<Stock, Double> fixedDividend;
	public static volatile SingularAttribute<Stock, Double> parValue;
	public static volatile SingularAttribute<Stock, Double> tickerPrice;
}
