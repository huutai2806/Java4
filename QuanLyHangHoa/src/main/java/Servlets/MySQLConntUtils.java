package Servlets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQLConntUtils {
	public static Connection getMySQLConnection() throws ClassNotFoundException,
	SQLException{
	String hostname="localhost";
	String dbName="quanlybanhang";
	String username="root";
	String password="";
	return getMySQLConnection(hostname,dbName,username, password);
	}
	private static Connection getMySQLConnection(String hostname, String dbName, String
	username, String password)
	throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	String connectionURL="jdbc:mysql://"+hostname+":3306/"+dbName;
	Connection conn=DriverManager.getConnection(connectionURL, username, password);
	return conn;
}
}