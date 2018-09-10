package com.arnavinc.ContactServices;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.arnavinc.persistentunits.Contact;
import com.arnavinc.utils.SessionUtils;
import com.arnavinc.utils.Utils;

public class ContactManager {
	
	public List<Contact> list(int limit, int offset, String keyword){
		Session session = SessionUtils.getSession(); 
		String hql = "From Contact";
		
		if(keyword != null) {
			keyword="%" + keyword + "%";
			hql = "from Contact where firstName like :keyword or lastName like :keyword";
		}
		Query query = session.createQuery(hql);
		if(keyword != null) {
			query.setParameter("keyword",keyword);
		}
		if (limit != 0 && offset != 0)
		{
			query.setFirstResult(limit);
			query.setMaxResults(offset);
		}
		List<Contact> contacts;
		contacts = query.list();
		session.close();
		return contacts;
	}

	
	public void addContact(Session session, Contact contact) {
		contact.setId(Utils.getGuid());
		session.save(contact); 
		
	}
	
	public Response addContact(Contact contact) {
		Session session = SessionUtils.getSession();
		Transaction tx = session.beginTransaction();
		addContact(session, contact);
		tx.commit();
		session.close();
		return Response.ok(contact.getId(), MediaType.APPLICATION_JSON).build(); 
	}
	
	public Contact getContact(String id) {
		try {
			Session session = SessionUtils.getSession();
			String hql = "from Contact record where id = :id";
			Query query = session.createQuery(hql);
			query.setString("id", id);
			Contact contact = (Contact)query.getSingleResult();
			session.close();
			return contact;
		}catch(Exception e) {
			return null;
		}
	}
	
	public void deleteContact(String id) {
		Session session = SessionUtils.getSession();
		
		String hql = "from Contact where id = :id";
		Query query = session.createQuery(hql);
		query.setString("id", id);
		Contact contact = (Contact)query.getSingleResult();
		Transaction tx = session.beginTransaction();
		session.delete(contact);
		tx.commit();
		session.close();
	}
	
	public void updateContact(String id, Contact contact) {
		Session session = SessionUtils.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from Contact record where id = :id";
		Query query = session.createQuery(hql);
		query.setString("id", id);
		Contact oldContact = (Contact)query.getSingleResult();
		oldContact.setFirstName(contact.getFirstName());
		oldContact.setLastName(contact.getLastName());
		oldContact.setPhone(contact.getPhone());
		session.save(oldContact);
        tx.commit();
        session.close();
	}
		
}
