package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    // Các thông tin kết nối đến cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/qlcf";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Phương thức để thiết lập kết nối đến cơ sở dữ liệu và trả về kết nối
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Phương thức để đóng kết nối
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đóng kết nối đến cơ sở dữ liệu thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
