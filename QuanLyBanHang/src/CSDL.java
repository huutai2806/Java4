import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CSDL {
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyhanghoa","root","");
		return con;
	}
	
	public List<Hanghoa> getHanghoa() throws SQLException{
		Connection cnn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Hanghoa> lstHanghoa=new ArrayList<Hanghoa>();
		try {
			cnn=this.getConnection();
			ps=cnn.prepareStatement("Select * from hanghoa");
			rs=ps.executeQuery();
			while(rs.next()) {
				lstHanghoa.add(new Hanghoa(rs.getString("MaHH"),rs.getString("TenHH"),rs.getInt("Gia"),rs.getString("Xuatxu"),rs.getString("LoaiHH")));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps==null) {
				ps.close();
			}
			if(cnn==null) {
				cnn.close();
			}
		}
		
		return lstHanghoa;
	}
}
