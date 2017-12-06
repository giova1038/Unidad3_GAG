package com.mkyong;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import com.mkyong.util.HibernateUtil;
import com.mkyong.stock.Category;
import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDetail;
import com.mkyong.user.DBUser;

public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		DBUser user = new DBUser();

		user.setUserId(100);
		user.setUsername("superman");
		user.setCreatedBy("system");
		user.setCreatedDate(new Date());
		session.saveOrUpdate(user);
		
		user = new DBUser();
		user.setUserId(101);
		user.setUsername("Batman");
		user.setCreatedBy("Superman");
		user.setCreatedDate(new Date());
		session.saveOrUpdate(user);
		
		Stock stock = new Stock();

		stock.setStockCode("7052");
		stock.setStockName("PADINI");

		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("PADINI Holding Malaysia");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("vinci vinci");
		stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);
		session.saveOrUpdate(stock);
		
		Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        session.saveOrUpdate(stock);
		
		session.getTransaction().commit();
	}
}
