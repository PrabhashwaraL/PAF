package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	public Connection connection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAF", "root", "admin123");
			System.out.println("Successfuly Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
		
		try {
			Connection connection = connection();
			if(connection == null) {
				return "Error while connecting to the db";
			}
			
			String query = "insert into Lab3_1(itemCode, itemName, itemPrice, itemDesc)"
			+ " values(?, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setDouble(3, Double.parseDouble(price));
			ps.setString(4, desc);
			
			ps.execute();
			connection.close();
			
			output = "Inserted Successfully";
		} catch (Exception e) {
			output = "Error while inserting data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readItem() {
		String output;
		try {
			Connection connection = connection();
			if(connection == null) {
				return "Error while connecting to the db";
			}
			
			output = "<table border=\"1\"><tr><th>Item Code</th>" + "<th>Item Name</th><th>Item Price</th>"
			+ "<th>Item Description</th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from Lab3_1";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String itemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");
				
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
				output += "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"itemID\" type=\"hidden\" value=\""
				+ itemID + "\">"
						+ "<input name=\"btnUpdate\" " + "type=\"button\" value=\"Update\"></form></td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" " + "type=\"submit\" value=\"Remove\""
						+ " class=\"btn btn-danger\">"
				+ "<input name=\"itemID\" type=\"hidden\" " + " value=\"" + itemID + "\">" + "</form></td></tr>";
			}
			
			connection.close();
			
			output += "</table>";
		} catch(Exception e) {
			output = "Error while inserting data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteItem(String itemID) {
		String output;
		
		try {
			Connection connection = connection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from Lab3_1 where itemID=?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(itemID));
			
			ps.execute();
			connection.close();
			
			output = "Delete successfully";
		} catch(Exception e){
			output = "Error while deleting the item.";System.err.println(e.getMessage());
		} 
		
		return output;
	}

}
