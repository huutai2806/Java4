package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.Member;

public class DBConnect {
 private Connection getConnection() throws ClassNotFoundException, SQLException {
  Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webhandmadeleather","root","");
  return con;
	}
 public int insertProduct(Product p) {
  Connection con=null;
  PreparedStatement ps=null;
  int row=0;
  try {
	  con=this.getConnection();
	  ps=con.prepareStatement("insert into Product value(?,?,?,?,?,?,?,?)");
	  ps.setString(1, p.getProductID());
	  ps.setString(2, p.getProductName());
	  ps.setString(3, p.getProductType());
	  ps.setDouble(4, p.getProductPrice());
	  ps.setString(5, p.getProductImg());
	  ps.setBoolean(6, p.isTrend());
	  ps.setBoolean(7, p.isPopular());
	  ps.setBoolean(8, p.isSaleoff());
	  row=ps.executeUpdate();
  } catch(ClassNotFoundException e) {
	// TODO Auto-generated catch block
	  e.printStackTrace();
  } catch(SQLException e) {
	// TODO Auto-generated catch block
	  e.printStackTrace();
  }
 return row;
 }
 public int insertMember(Member m) {
	 Connection con=null;
	 PreparedStatement ps=null;
	 int row=0;
	 try {
		 con=this.getConnection();
		 ps=con.prepareStatement("insert into Member value(?,?,?)");
		 ps.setString(1, m.getName());
		 row=ps.executeUpdate();
	 } catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
		  e.printStackTrace();
	  } catch(SQLException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
 }
 return row;
 }
 
 public List<Product> selectTrendProduct() throws SQLException {
	 Connection cnn=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	 List<Product> productList=new ArrayList<Product>();
	 try {
		 cnn=this.getConnection();
		 ps=cnn.prepareStatement("select * from Product where Trend=true");
		 rs=ps.executeQuery();
		 Product p;
		 while(rs.next()) {
			 p=new Product(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"), rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("Trend"),rs.getBoolean("Popular"),rs.getBoolean("Saleoff"));
			 productList.add(p);
		 }
	 } catch(Exception e) {
		 System.out.print(e);
	 }
	 finally {
		 if(ps!=null)
			 ps.close();
		 if(cnn!=null)
			 cnn.close();
	 }
	 return productList;
 }
 public List<Product> selectPopularProduct() throws SQLException {
	 Connection cnn=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	 List<Product> productList=new ArrayList<Product>();
	 try {
		 cnn=this.getConnection();
		 ps=cnn.prepareStatement("select * from Product where Popular=true");
		 rs=ps.executeQuery();
		 Product p;
		 while(rs.next()) {
			 p=new Product(rs.getString("ID"),rs.getString("Name"),rs.getString("Type"),rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("Trend"),rs.getBoolean("Popular"),rs.getBoolean("Saleoff"));
			 productList.add(p);
		 }
	 } catch(Exception e) {
		 System.out.print(e);
	 }
	 finally {
		 if(ps!=null)
			 ps.close();
		 if(cnn!=null)
			 cnn.close();
	 }
	 return productList;
   } 
   public List<Product> selectallProduct() throws SQLException {
		 Connection cnn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<Product> productList=new ArrayList<Product>();
		 try {
			 cnn=this.getConnection();
			 ps=cnn.prepareStatement("select * from Product ");
			 rs=ps.executeQuery();
			 Product p;
			 while(rs.next()) {
				 p=new Product(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"), rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("Trend"),rs.getBoolean("Popular"),rs.getBoolean("Saleoff"));
				 productList.add(p);
			 }
		 } catch(Exception e) {
			 System.out.print(e);
		 }
		 finally {
			 if(ps!=null)
				 ps.close();
			 if(cnn!=null)
				 cnn.close();
		 }
		 return productList;
   }
   public List<Product> findProductByName(String name) throws SQLException {
		 Connection cnn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<Product> productList=new ArrayList<Product>();
		 try {
			 cnn=this.getConnection();
			 ps=cnn.prepareStatement("select * from Product where Name like ?");
			 ps.setString(1,"%" + name + "%");
			 rs=ps.executeQuery();
			 Product p;
			 while(rs.next()) {
				 p=new Product(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"), rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("Trend"),rs.getBoolean("Popular"),rs.getBoolean("Saleoff"));
				 productList.add(p);
			 }
		 } catch(Exception e) {
			 System.out.print(e);
		 }
		 finally {
			 if(ps!=null)
				 ps.close();
			 if(cnn!=null)
				 cnn.close();
		 }
		 return productList;
   }
   public List<Product> findProduct(String id) throws SQLException {
		 Connection cnn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<Product> productList=new ArrayList<Product>();
		 try {
			 cnn=this.getConnection();
			 ps=cnn.prepareStatement("select * from Product where id = ?");
			 ps.setString(1, id);
			 rs=ps.executeQuery();
			 Product p;
			 while(rs.next()) {
				 p=new Product(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"), rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("Trend"),rs.getBoolean("Popular"),rs.getBoolean("Saleoff"));
				 productList.add(p);
			 }
		 } catch(Exception e) {
			 System.out.print(e);
		 }
		 finally {
			 if(ps!=null)
				 ps.close();
			 if(cnn!=null)
				 cnn.close();
		 }
		 return productList;
   }
   public int updateProduct(Product p) {
		 Connection con=null;
		 PreparedStatement ps=null;
		 int row=0;
		 try {
			 con=this.getConnection();
			 ps=con.prepareStatement("update product set id=?, name=?, type=?, price=?, image=?, trend=?, popular=?, saleoff=? where id=?");
			 ps.setString(1, p.getProductID());
			  ps.setString(2, p.getProductName());
			  ps.setString(3, p.getProductType());
			  ps.setDouble(4, p.getProductPrice());
			  ps.setString(5, p.getProductImg());
			  ps.setBoolean(6, p.isTrend());
			  ps.setBoolean(7, p.isPopular());
			  ps.setBoolean(8, p.isSaleoff());
			  ps.setString(9, p.getProductID());
			  row=ps.executeUpdate();
		  } catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		  } catch(SQLException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		 return row;
	 }
}
 
