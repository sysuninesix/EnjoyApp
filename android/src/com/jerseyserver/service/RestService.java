package com.jerseyserver.service;


import java.util.HashMap;
import com.jerseyserver.entity.*;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerseyserver.entity.ResponseDTO;
import com.jerseyserver.entity.User;
import com.jerseyserver.util.TextUtil;
import java.sql.*;


@Path("/restService")
public class RestService {

	String driver = "com.mysql.jdbc.Driver";   
    String url = "jdbc:mysql://127.0.0.1:3306/test";   
    String user = "root";   
    String password = "jiba123xie";   
	
	void insert(String username, String pass){
		 System.out.println("Succeeded connecting to the Database!");   
	    try {   
	    	
            Class.forName(driver);   
            Connection conn = DriverManager.getConnection(url, user, password);   
               
            String insql="insert into android(NAME,PASSWORD) values(?,?)";  
            PreparedStatement ps=conn.prepareStatement(insql);
            ps.setString(1,username);
            ps.setString(2,	pass );
            int result=ps.executeUpdate();
                conn.close();
            
        } catch (Exception e) {   
            e.printStackTrace();   
  
        }  	    
	}
	
	boolean query(String username, String pass) {
		boolean log = false;
		try {   
            Class.forName(driver);   
            Connection conn = DriverManager.getConnection(url, user, password);   
            if (!conn.isClosed()) {   
                System.out.println("Succeeded connecting to the Database!");     
                String sql = "select * from android where NAME = ? ";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();    
               while(rs.next()){
            	   if (pass.equals(rs.getString(2)))
            		   log= true;
            	   
               }
                conn.close();
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  	    
		System.out.println(log);
		return log;
	}
	
	@GET
	@Path("/getUserXml")
	@Produces(MediaType.APPLICATION_XML)
	/**
	 * 测试返回xml媒体类型数据的方式
	 * @return User object
	 */
	public User getUserXml() {
		User user = new User();
		user.setName("liuyuxin");
	
		
		return user;
	}
	
	@GET
	@Path("/getUsertest")
	@Produces(MediaType.APPLICATION_XML)
	/**
	 * 测试返回xml媒体类型数据的方式
	 * @return User object
	 */
	public User getUsertest() {

		User user = new User();
		System.out.println(query("2","2"));
		user.setName("liuyuxin");
		return user;
	}
	@GET
	@Path("/getUserJson")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * 测试返回json媒体类型数据的方式
	 * @return User object
	 */
	public User getUserJson() {
		User user = new User();
		user.setName("liuyuxin");
		return user;
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@FormParam("username") String username, @FormParam("password")String password) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(password);
		if(!query(username,password)){
		   insert(username,password);
		   System.out.println("here!"); 
		        GenericEntity<String> payloadEntity;
		        try {
		            payloadEntity = new GenericEntity<String>(
		                    mapper.writeValueAsString(new ResponseDTO(200, "ok", user))) {
		            };
		            return Response.ok(payloadEntity).build();
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		            return Response
		                    .ok(" {\"status\": 404,\n\"message\": \"error\",\n\"response\": \"\"}")
		                    .build();	 
			}
		        }
		        else
		        	return null;

		}
	
		   
		
		 

		
	
	

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response login(@FormParam("username") String username, @FormParam("password")String password ) {
ObjectMapper mapper = new ObjectMapper();
  System.out.println(username);
        if (query(username,password)){
        GenericEntity<String> payloadEntity;
        try {
            payloadEntity = new GenericEntity<String>(
                    mapper.writeValueAsString(new ResponseDTO(200, "ok", user))) {
            };
            return Response.ok(payloadEntity).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response
                    .ok(" {\"status\": 404,\n\"message\": \"error\",\n\"response\": \"\"}")
                    .build();
		 
		 
	}
        }
        else
        	return null;

}
}
	
		
	
	
