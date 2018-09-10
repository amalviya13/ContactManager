package com.arnavinc.ContactServices;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.arnavinc.persistentunits.User;
import com.arnavinc.utils.SessionUtils;
import com.arnavinc.utils.Utils;

public class UserManager {
	
	public void addUser(Session session, User User) {
		User.setId(Utils.getGuid());
		session.save(User); 
	}
	
	public Response addUser(User User) {
		Session session = SessionUtils.getSession();
		Transaction tx = session.beginTransaction();
		addUser(session, User);
		tx.commit();
		session.close();
		return Response.ok(User.getId(), MediaType.APPLICATION_JSON).build(); 
	}
	
	public User getUser(String id) {
		try {
			Session session = SessionUtils.getSession();
			String hql = "from User record where id = :id";
			Query query = session.createQuery(hql);
			query.setString("id", id);
			User User = (User)query.getSingleResult();
			session.close();
			return User;
		}catch(Exception e) {
			return null;
		}
	}
	
	public void deleteUser(String id) {
		Session session = SessionUtils.getSession();
		
		String hql = "from User where id = :id";
		Query query = session.createQuery(hql);
		query.setString("id", id);
		User User = (User)query.getSingleResult();
		Transaction tx = session.beginTransaction();
		session.delete(User);
		tx.commit();
		session.close();
	}
	
	public void updateUser(String id, User User) {
		Session session = SessionUtils.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from User record where id = :id";
		Query query = session.createQuery(hql);
		query.setString("id", id);
		User oldUser = (User)query.getSingleResult();
		oldUser.setFirstName(User.getFirstName());
		oldUser.setLastName(User.getLastName());
		oldUser.setEmail(User.getEmail());
		session.save(oldUser);
        tx.commit();
        session.close();
	}
}
