package com.jpmorgan.stocks.simple.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jpmorgan.stocks.simple.dao.StockDAO;
import com.jpmorgan.stocks.simple.model.Stock;

@Repository("stockDAO")
public class StockDAOImpl implements StockDAO {
	/**
	 * Logger service for the class.
	 */
	private Logger logger = Logger.getLogger(StockDAOImpl.class);
	private EntityManager em;
	
	StockDAOImpl()
	{
		em = Persistence
				.createEntityManagerFactory("supersimplestock")
				.createEntityManager();
		
	}

	@Override
	public List<Stock> listStock() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Stock> criteria = builder.createQuery(Stock.class);
		Root<Stock> Stock = criteria.from(Stock.class);
		criteria.select(Stock);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Stock getStock(Long id) {
		return em.find(Stock.class, id);
	}

	@Override
	public Stock getStock(String stockSimbol) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Stock> criteria = builder.createQuery(Stock.class);
		Root<Stock> cu = criteria.from(Stock.class);
		ParameterExpression<String> pStockSimbol = builder.parameter(String.class);
		Predicate predicate1 = builder.equal(cu.get("stockSymbol"), pStockSimbol); 
		criteria.select(cu).where(builder.and(predicate1));
		try {
			return em.createQuery(criteria).setParameter(pStockSimbol , stockSimbol ).
					getSingleResult();
		} catch (Exception e) {
			logger.debug("getStock(String stockSimbol : " + stockSimbol + 
					" ) : " + e.getMessage());
			return null;
		}
	}

	@Override
	public long addStock(Stock stock) {
		em.getTransaction().begin();
		em.persist(stock);
		em.getTransaction().commit();

		return stock.getId();
	}

}
