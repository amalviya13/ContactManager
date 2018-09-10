package com.arnavinc.restinterfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arnavinc.ContactServices.UserManager;
import com.arnavinc.persistentunits.User;
@Path("/users")

public class UserResource {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUserDetails(@PathParam("id") String id){
		UserManager UserManager = new UserManager();
		return UserManager.getUser(id); 
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addUser(User User) {
		 User.setFirstName(User.getFirstName());
		 User.setLastName(User.getLastName());
		 User.setId(User.getId());
		 User.setEmail(User.getEmail());
		 UserManager cM = new UserManager();
		 return cM.addUser(User);
	 }
	 
	 @DELETE
	 @Path("{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteUser(@PathParam("id") String id) {
		 UserManager User = new UserManager();
		 User.deleteUser(id);
		 
	 }
	 
	 @PUT
	 @Path("{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void updateUser(@PathParam("id") String id,User updatedUser) {
		 UserManager User = new UserManager();	
		 User.updateUser(id,updatedUser);
	 }
}
