package edu.excilys.cdb.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;

public class ConnectionMYSQL {
	
	private String URL;
	private String user;
	private String password;
	
	private static Connection connect;
	
	private ConnectionMYSQL() throws IOException{

		Properties prop = readPropertiesFile("project.properties");
		this.URL = prop.getProperty("server");
		this.user = prop.getProperty("username");
		this.password = prop.getProperty("password");

		try {
			connect = DriverManager.getConnection(this.URL, this.user, this.password);
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getInstance() {
		if(connect == null) {
			try {
				new ConnectionMYSQL();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null) {
			try {
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
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
