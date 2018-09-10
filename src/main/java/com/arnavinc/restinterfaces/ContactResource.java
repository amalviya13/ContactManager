package com.arnavinc.restinterfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.arnavinc.ContactServices.ContactManager;
import com.arnavinc.persistentunits.Contact;

@Path("/contacts")
public class ContactResource {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Contact> listContacts(@QueryParam("limit") int limit, @QueryParam("offset") int offset, @QueryParam("keyword") String keyword){
		
		ContactManager contactManager = new ContactManager();
		return contactManager.list(limit, offset, keyword);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Contact getContactDetails(@PathParam("id") String id){
		ContactManager contactManager = new ContactManager();
		return contactManager.getContact(id); 
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addContact(Contact contact) {
		 contact.setFirstName(contact.getFirstName());
		 contact.setLastName(contact.getLastName());
		 contact.setId(contact.getId());
		 contact.setPhone(contact.getPhone());
		 ContactManager cM = new ContactManager();
		 return cM.addContact(contact);
	 }
	 
	 @DELETE
	 @Path("{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteContact(@PathParam("id") String id) {
		 ContactManager contact = new ContactManager();
		 contact.deleteContact(id);
		 
	 }
	 
	 @PUT
	 @Path("{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void updateContact(@PathParam("id") String id,Contact updatedContact) {
		 ContactManager contact = new ContactManager();	
		 contact.updateContact(id,updatedContact);
	 }
}




