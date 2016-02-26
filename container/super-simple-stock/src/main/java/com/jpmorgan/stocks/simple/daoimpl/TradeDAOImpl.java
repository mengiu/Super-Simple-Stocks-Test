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

import com.jpmorgan.stocks.simple.arch.impl.SpringServiceImpl;
import com.jpmorgan.stocks.simple.dao.TradeDAO;
import com.jpmorgan.stocks.simple.model.Trade;

@Repository("tradeDAO")
public class TradeDAOImpl implements TradeDAO{
	/**
	 * Logger service for the class.
	 */
	private Logger logger = Logger.getLogger(TradeDAOImpl.class);
	private EntityManager em;
	
	TradeDAOImpl()
	{
		em = Persistence
				.createEntityManagerFactory("supersimplestock")
				.createEntityManager();
		
	}

	@Override
	public List<Trade> listTrade() {

		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Trade> criteria = builder.createQuery(Trade.class);
		Root<Trade> trade = criteria.from(Trade.class);
		criteria.select(trade);
		return em.createQuery(criteria).getResultList();
		
	}

	@Override
	public Trade getTrade(Long id) {
		return em.find(Trade.class, id);
	}

	@Override
	public Trade getTrade(String stockSimbol) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Trade> criteria = builder.createQuery(Trade.class);
		Root<Trade> cu = criteria.from(Trade.class);
		ParameterExpression<String> pStockSimbol = builder.parameter(String.class);
		Predicate predicate1 = builder.equal(cu.get("stock").get("stockSymbol"), pStockSimbol); 
		criteria.select(cu).where(builder.and(predicate1));
		try {
			return em.createQuery(criteria).setParameter(pStockSimbol , stockSimbol ).
					getSingleResult();
		} catch (Exception e) {
			logger.debug("getTrade(String stockSimbol : " + stockSimbol + 
					" ) : " + e.getMessage());
			return null;
		}
	}

	@Override
	public long addTrade(Trade trade){

			em.getTransaction().begin();
			em.persist(trade);
			em.getTransaction().commit();

			return trade.getId();
	}

}
