package main.java.edu.excilys.cdb.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMYSQL {
	
	private String url;
	private String user;
	private String password;
	
	private static ConnectionMYSQL INSTANCE;
	
	private static Connection connect;
	
	private ConnectionMYSQL(){
		Properties prop;
		//try {
			//prop = readPropertiesFile("project.properties");
			this.url = /*prop.getProperty("server")*/"jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false";
			this.user = /*prop.getProperty("username")*/ "admincdb";
			this.password = /*prop.getProperty("password")*/ "qwerty1234";
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static ConnectionMYSQL getInstance() {
		if(INSTANCE == null) 
			INSTANCE = new ConnectionMYSQL();
		return INSTANCE;
	}
	
	public Connection connect() {
		try {
			if(connect == null || connect.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection(url, user, password);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public void disconnect() {
		try {
			if(connect != null) {					
				connect.close();
				connect = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
}
