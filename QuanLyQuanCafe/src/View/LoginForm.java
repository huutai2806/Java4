package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private MainMenuCafe mainmenucafe;
    private RegisterForm registerForm;

    public LoginForm() {
        setTitle("Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Tài Khoản:");
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Mật Khẩu:");
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Đăng Nhập");
        btnRegister = new JButton("Đăng Ký");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        getContentPane().add(panel);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        // Xử lý sự kiện khi nhấn nút Đăng Nhập
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());

                // Kiểm tra thông tin đăng nhập ở đây, nếu hợp lệ:
                boolean loginSuccess = performLogin(username, password);

                if (loginSuccess) {
                    setVisible(false); // Ẩn form đăng nhập
                    MainMenuCafe mainmenucafe = new MainMenuCafe(); // Khởi tạo form LibraryMenu
                    mainmenucafe.setVisible(true); // Hiển thị form LibraryMenu
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập không thành công. Vui lòng thử lại!");
                }
            }
        });

        // Xử lý sự kiện khi nhấn nút Đăng Ký
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Ẩn form đăng nhập
                registerForm = new RegisterForm(); // Hiển thị form đăng ký
                registerForm.setVisible(true);
            }
        });
    }

    // Phương thức giả định kiểm tra thông tin đăng nhập
    private boolean performLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");

            String query = "SELECT * FROM Login WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasUser = resultSet.next(); // Kiểm tra nếu có người dùng tồn tại

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return hasUser; // Trả về true nếu có người dùng tồn tại, ngược lại trả về false
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi xảy ra trong quá trình kiểm tra
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}
