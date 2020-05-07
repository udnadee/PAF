package controller;

import java.sql.*;

public class HospitalDBHandler {
	
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readHospital() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}
			
			// Prepare the html table to be displayed
		output = "<table class='displayTable'><thead><tr><th>Hospital ID</th><th>Registration No</th><th>Name</th><th>Type</th>"
				+ "<th>Addressline</th><th>city</th><th>district</th><th>province</th><th>Contact</th><th>Fee</th><th>Update</th><th>Delete</th></tr></thead><tbody id='myTable1'>";
						
						
			String query = "select * from hospital";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next())
			{
				String hos_id = Integer.toString(resultSet.getInt(1));
				String hospital_reg_no = resultSet.getString(2);
				String hos_name = resultSet.getString(3);
				String type_private = resultSet.getString(4);
				String AddressLine1 = resultSet.getString(5);
				String city = resultSet.getString(6);
				String district = resultSet.getString(7);
				String province = resultSet.getString(8);
				String telephone = resultSet.getString(9);
				String hospital_fee = Double.toString(resultSet.getDouble(10));
					
					// add to html table
					output += "<tr><td><input id='hidHosIDUpdate' name='hidHosIDUpdate' type='hidden' value='" + hos_id + "'>"+hos_id+"</td>";
					output += "<td>" + hospital_reg_no + "</td>";
					output += "<td>" + hos_name + "</td>";
					output += "<td>" + type_private + "</td>";
					output += "<td>" + AddressLine1 + "</td>";
					output += "<td>" + city + "</td>";
					output += "<td>" + district + "</td>";
					output += "<td>" + province + "</td>";
					output += "<td>" + telephone + "</td>";
					output += "<td>" + hospital_fee + "</td>";
					
					// buttons.
					output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>";
					output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hosid='" + hos_id + "'> </td></tr>";
					
				}

				con.close();

				output += "</tbody></table>";
				
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String HospitalInsert(
			 String hospital_reg_no,
			 String hos_name,
			 String hos_type,
			 String AddressLine1,
			 String city,
			 String district,
			 String province,
			 String telephone,
			 String hospital_fee,
			 String hos_password) {
		
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into hospital (hospital_reg_no,hos_name,hos_type,"
					+ "AddressLine1,city,district,province,telephone,hospital_fee,hos_password) "
					+ "values (?,?,?,?,?,?,?,?,?,?) ";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hospital_reg_no);
			preparedStatement.setString(2, hos_name);
			preparedStatement.setString(3, hos_type);
			preparedStatement.setString(4, AddressLine1);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, district);
			preparedStatement.setString(7, province);
			preparedStatement.setString(8, telephone);
			preparedStatement.setDouble(9, Double.parseDouble(hospital_fee));
			preparedStatement.setString(10, hos_password);
			

			// execution
			preparedStatement.execute();
			con.close();

			String newHospitals = readHospital();
			output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting.\"}";
					System.err.println(e.getMessage());
		}

		return output;

	}
	
	public String updateHos(String hos_id ,	String hos_name,String hos_type,String AddressLine1,String city,String district, String province, String telephone,String hospital_fee) 
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE hospital SET hos_name=?,hos_type=?,AddressLine1=?,city=?,district = ?, province=?,telephone=?,hospital_fee=?"
					+ " WHERE hos_id  =?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hos_name);
			preparedStatement.setString(2, hos_type);
			preparedStatement.setString(3, AddressLine1);
			preparedStatement.setString(4, city);
			preparedStatement.setString(5, district);
			preparedStatement.setString(6, province);
			preparedStatement.setString(7, telephone);
			preparedStatement.setDouble(8, Double.parseDouble(hospital_fee));
			preparedStatement.setString(9, hos_id);
			// execution
			preparedStatement.execute();
			con.close();

			String newHospitals = readHospital();
			
			output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating. \"}";
			System.err.println(e.getMessage());
			;
		}

		return output;
	}
	
	public String updateHosPassword(String hospital_reg_no,String password) 
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE hospital SET hos_password=?"
					+ " WHERE hospital_reg_no =?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, hospital_reg_no);
			// execution
			preparedStatement.execute();
			con.close();

			output = "Updated Successfully";

		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
			;
		}

		return output;
	}
	
	public String deleteHospital(String ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from hospital where hos_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospitals = readHospital();
			output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readAllHospitalView() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table class='displayTable' id='displayTable'><thead><tr><th>Hospital ID</th><th>Name</th><th>Type</th>"
					+ "<th>Addressline</th><th>city</th><th>Contact</th><th>Fee</th></tr></thead><tbody id='myTable'>";
			
			String query = "select * from hospital";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hos_id = Integer.toString(resultSet.getInt(1));
				String hos_name = resultSet.getString(3);
				String type_private = resultSet.getString(4);
				String AddressLine1 = resultSet.getString(5);
				String city = resultSet.getString(6);
				String telephone = resultSet.getString(9);
				String hospital_fee = Double.toString(resultSet.getDouble(10));

				
				// add to html table
				output += "<tr><td>" + hos_id + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + type_private + "</td>";
				output += "<td>" + AddressLine1 + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				
			}

			con.close();

			output += "</tbody></table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
	public String readAllHospital() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table class='displayTable'><thead><tr><th>Hospital ID</th><th>Registration No</th><th>Name</th><th>Type</th>"
					+ "<th>Addressline</th><th>city</th><th>district</th><th>province</th><th>Contact</th><th>Fee</th><th>Delete</th></tr></thead><tbody id='myTable1'>";
							
			String query = "select * from hospital order by hos_id desc";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hos_id = Integer.toString(resultSet.getInt(1));
				String hospital_reg_no = resultSet.getString(2);
				String hos_name = resultSet.getString(3);
				String type_private = resultSet.getString(4);
				String AddressLine1 = resultSet.getString(5);
				String city = resultSet.getString(6);
				String district = resultSet.getString(7);
				String province = resultSet.getString(8);
				String telephone = resultSet.getString(9);
				String hospital_fee = Double.toString(resultSet.getDouble(10));

				
				// add to html table
				output += "<tr><td><input id='hidHosIDUpdate' name='hidHosIDUpdate' type='hidden' value='" + hos_id + "'>"+hos_id+"</td>";
				output += "<td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + type_private + "</td>";
				output += "<td>" + AddressLine1 + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + district + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				
				// buttons.
				//output += "<td><input name='btnUpdate'type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>";
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btn btn-danger' data-hosid='"+ hos_id + "'></td></tr>";
				//output +="<td><form method='post' action='admin_page.jsp'><input name='hidHosIDDelete' type='hidden' value='" + hos_id + "'></form></td></tr>";
			}

			con.close();

			output += "</tbody></table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String loginAdmin(String un, String pw)
	{
		String output = "";
		
		if (!un.equals("Admin"))//Hard coded test value
		{
			output = "Invalid username";
		}
		else if (un.equals("Admin"))//Hard coded test value
		{
			if (!pw.equals("Admin123"))//Hard coded test value
			{
				output = "Invalid password";
			}
			else if (pw.equals("Admin123"))//Hard coded test value
			{
				output = "success";
			}
		}
		return output;
	}
	
	public String loginHos(String regNo, String password) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {

				 output=  "Error while connecting database";
			}
			
			String query = "select hos_id from hospital where hospital_reg_no = '"+regNo + "' and hos_password = '" +password +"'";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if(resultSet.next() == 	false) 
			{
				output = "Invalid Account";
			}
			else if(resultSet.next() == true) 
			{
			
				String hos_id = resultSet.getString(1);
					
				System.out.println("hospital_reg_no" + hos_id);		
				output=  "success";
			}
			 con.close();
		}
		catch(Exception e) {
			output= "Error while reading";
			output += e.getMessage();
		}
		
		return output;
	}
	
}
