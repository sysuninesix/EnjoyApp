package com.jerseyserver.service;


import java.util.ArrayList;
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
	
	ArrayList<Integer> checkseat(int movie, int row) {
		ArrayList<Integer> arraylist= new ArrayList<Integer>();
		boolean check = false;
		String sql="";
		String sql1 = "";
		try {   
            Class.forName(driver);   
            Connection conn = DriverManager.getConnection(url, user, password); 
            Connection conn1 = DriverManager.getConnection(url, user, password); 
            if (!conn.isClosed()) {   
                System.out.println("Succeeded connecting to the Cinemabase!");
                String table = "";
                switch(movie) {
                case 1:
                	table = "cinema1";
                	sql = "select * from cinema1 where id = ? ";
                	sql1="update cinema1 set ";  
                	break;
                case 2:
                	table = "cinema2";
                	sql = "select * from cinema2 where id = ? ";
                	sql1="update cinema2 set "; 
                	break;
                case 3:
                	table = "cinema3";
                	sql = "select * from cinema3 where id = ? ";
                	sql1="update cinema3 set "; 
                	break;
                case 4:
                	table = "cinema4";
                	sql = "select * from cinema4 where id = ? ";
                	sql1="update cinema4 set "; 
                	break;
                case 5:
                	table = "cinema5";
                	sql = "select * from cinema5 where id = ? ";
                	sql1="update cinema5 set "; 
                	break;
                case 6:
                	table = "cinema6";
                	sql = "select * from cinema6 where id = ? ";
                	sql1="update cinema6 set "; 
                	break;
                default:
                	break;
                }
                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1, row);
                ResultSet rs = ps.executeQuery(); 
                while(rs.next()){
                	for (int i= 2;i<=15;i++ )
                	if (rs.getInt(i) == 1)
                	  arraylist.add(i-1);
                }
                
                conn.close();
                conn1.close();
            }
        } catch (Exception e) {   
            e.printStackTrace();   
        }  	    
		System.out.println(check);
		return arraylist;
	}
	
	boolean bookseat(int movie, int row, int column) {
		boolean book = false;
		String sql="";
		String sql1 = "";
		try {   
            Class.forName(driver);   
            Connection conn = DriverManager.getConnection(url, user, password); 
            Connection conn1 = DriverManager.getConnection(url, user, password); 
            if (!conn.isClosed()) {   
                System.out.println("Succeeded connecting to the Cinemabase!");
                String table = "";
                switch(movie) {
                case 1:
                	table = "cinema1";
                	sql = "select * from cinema1 where id = ? ";
                	sql1="update cinema1 set ";  
                	break;
                case 2:
                	table = "cinema2";
                	sql = "select * from cinema2 where id = ? ";
                	sql1="update cinema2 set "; 
                	break;
                case 3:
                	table = "cinema3";
                	sql = "select * from cinema3 where id = ? ";
                	sql1="update cinema3 set "; 
                	break;
                case 4:
                	table = "cinema4";
                	sql = "select * from cinema4 where id = ? ";
                	sql1="update cinema4 set "; 
                	break;
                case 5:
                	table = "cinema5";
                	sql = "select * from cinema5 where id = ? ";
                	sql1="update cinema5 set "; 
                	break;
                case 6:
                	table = "cinema6";
                	sql = "select * from cinema6 where id = ? ";
                	sql1="update cinema6 set "; 
                	break;
                default:
                	break;
                }
                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1, row);
                ResultSet rs = ps.executeQuery();    
               while(rs.next()){
            	   System.out.println(sql);
            	   System.out.println(rs.getInt(column+1));
            	   if (rs.getInt(column+1) == 0){
            		   String c = "";
            		   switch(column) {
            		   case 1:
            			   c = "c1 = 1 where id = ?";
            			   break;
            		   case 2:
            			   c = "c2 = 1 where id = ?";
            			   break;
            		   case 3:
            			   c = "c3 = 1 where id = ?";
            			   break;
            		   case 4:
            			   c = "c4 = 1 where id = ?";
            			   break;
            		   case 5:
            			   c = "c5 = 1 where id = ?";
            			   break;
            		   case 6:
            			   c = "c6 = 1 where id = ?";
            			   break;
            		   case 7:
            			   c = "c7 = 1 where id = ?";
            			   break;
            		   case 8:
            			   c = "c8  = 1 where id = ?";
            			   break;
            		   case 9:
            			   c = "c9  = 1 where id = ?";
            			   break;
            		   case 10:
            			   c = "c10 = 1 where id = ?";
            			   break;
            		   case 11:
            			   c = "c11 = 1 where id = ?";
            			   break;
            		   case 12:
            			   c = "c12 = 1 where id = ?";
            			   break;
            		   case 13:
            			   c = "c13 = 1 where id = ?";
            			   break;
            		   case 14:
            			   c = "c14 = 1 where id = ?";
            			   break;
            			   default:
            				   break;
            		   }
            		   String sql2 = sql1 + c;
            		   System.out.println(sql2);
                   PreparedStatement ps1=conn1.prepareStatement(sql2);
                   ps1.setInt(1, row);
                   int result=ps1.executeUpdate();
                       conn.close();
                       conn1.close();
            		   book = true; 
            	   }
               }
                conn.close();
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  	    
		return book;
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
	private ArrayList<Integer> checkallseat(String movie, String count){
		ArrayList<Integer> arraylist= new ArrayList<Integer>();
		for (int i= 1; i<=10;i++){
			ArrayList<Integer> temp=checkseat(Integer.parseInt(movie),Integer.parseInt(count+i));
			for (int j= 0;j< temp.size();j++){
				arraylist.add(i);
				arraylist.add(temp.get(j));
				
			}
					
				}
			
		
			return arraylist;
		}
	
	@POST
	@Path("/book")
	@Produces(MediaType.APPLICATION_JSON)
	public Response book(@FormParam("movie") String movie, @FormParam("row")String row, @FormParam("column")String column) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(row);
		System.out.println(column);
		if(bookseat(Integer.parseInt(movie), Integer.parseInt(row), Integer.parseInt(column))){
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
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@FormParam("movie") String movie, @FormParam("count")String count) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Integer> arrayList=checkallseat(movie, count);
		String temp="";
		for (int i= 0;i< arrayList.size();i++){
			if (i!= 0)
			temp+=String.valueOf(","+arrayList.get(i)+",");
			else
				temp+=String.valueOf(arrayList.get(i)+",");
			temp+=String.valueOf(arrayList.get(++i));
		}
		   System.out.println(temp); 
		        GenericEntity<String> payloadEntity;
		        try {
		            payloadEntity = new GenericEntity<String>(
		                    mapper.writeValueAsString(new ResponseDTO(200, temp, user))) {
		            };
		            return Response.ok(payloadEntity).build();
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		            return Response
		                    .ok(" {\"status\": 404,\n\"message\": \"error\",\n\"response\": \"\"}")
		                    .build();	 
			}
		        

		}
	
}
	
		
	
