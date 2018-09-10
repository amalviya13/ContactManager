package com.arnavinc.utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class SessionUtils {

	private static SessionUtils instance = new SessionUtils();
	private static SessionFactory sessionFactory;
	private SessionUtils() {
		Configuration configuration = new Configuration();
	    configuration.configure("persistence.xml");
	            
	    sessionFactory = configuration.buildSessionFactory();
	}
	
	public static Session getSession() {
		Session session = sessionFactory.openSession();
		return session;
	}
	
	public static SessionUtils getInstance() {
		return instance;
	}
}

