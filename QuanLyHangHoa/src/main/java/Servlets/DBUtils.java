package Servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	public static UserAccount findUser(Connection conn, //
			String userName, String password) throws SQLException {
		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
				+ " where a.User_Name = ? and a.password= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			String gender = rs.getString("Gender");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
				+ " where a.User_Name = ? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static List<Product> queryProduct(Connection conn) throws SQLException {
		String sql = "Select a.Id, a.Name,a.Type, a.Price from Product a ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("Name");
			String type = rs.getString("Type");
			int price= rs.getInt("Price");
			
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setType(type);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}

	public static Product findProduct(Connection conn, String id, String image) throws SQLException {
		String sql = "Select a.Id, a.Name,a.Type, a.Price from Product a where a.Code=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String id1 = rs.getString("Id");
			String name = rs.getString("Name");
			String type = rs.getString("Type");
			int price = rs.getInt("Price");
			Product product = new Product(id1, name,type, price,image);
			return product;
		}
		return null;
	}

	public static void updateProduct(Connection conn, Product product) throws SQLException {
		String sql = "Update Product set Id =?, Name =?,Type =?, Price=? where Code=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getId());
		pstm.setString(2, product.getName());
		pstm.setString(3, product.getType());
		pstm.setInt(4, product.getPrice());
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into Product(Id, Name,Type, Price) values (?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getId());
		pstm.setString(2, product.getName());
		pstm.setString(3, product.getType());
		pstm.setInt(4, product.getPrice());
		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String id) throws SQLException {
		String sql = "Delete From Product where Code= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		pstm.executeUpdate();
	}

	public static void insertUserAccount(Connection conn, UserAccount user) throws SQLException {
		String sql = "Insert into UserAccount(Username, Gender, Password) values (?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getGender());
		pstm.setString(3, user.getPassword());
		pstm.executeUpdate();
	}
}