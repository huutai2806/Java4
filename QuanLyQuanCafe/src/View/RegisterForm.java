package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterForm extends JFrame {
    private JTextField txtUsername, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;

    public RegisterForm() {
        setTitle("Đăng Ký");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Tài Khoản:");
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Mật Khẩu:");
        txtPassword = new JPasswordField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        btnRegister = new JButton("Đăng Ký");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnRegister);

        getContentPane().add(panel);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        // Xử lý sự kiện khi nhấn nút Đăng Ký
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                String email = txtEmail.getText();

                // Thực hiện lưu thông tin vào CSDL
                boolean success = saveToDatabase(username, password, email);

                if (success) {
                    dispose(); // Đóng form đăng ký
                    LoginForm loginForm = new LoginForm(); // Hiển thị form đăng nhập
                    loginForm.setVisible(true);
                }
            }
        });
    }

    // Lưu thông tin đăng ký vào CSDL
    private boolean saveToDatabase(String username, String password, String email) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");

            // Thêm dữ liệu vào bảng 'login'
            String loginQuery = "INSERT INTO login (username, password) VALUES (?, ?)";
            PreparedStatement loginStatement = connection.prepareStatement(loginQuery);
            loginStatement.setString(1, username);
            loginStatement.setString(2, password);

            // Thêm dữ liệu vào bảng 'register'
            String registerQuery = "INSERT INTO register (username,password, email) VALUES (?, ?, ?)";
            PreparedStatement registerStatement = connection.prepareStatement(registerQuery);
            registerStatement.setString(1, username);
            registerStatement.setString(2, password);
            registerStatement.setString(3, email);

            int loginRowsAffected = loginStatement.executeUpdate();
            int registerRowsAffected = registerStatement.executeUpdate();

            if (loginRowsAffected > 0 && registerRowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                setVisible(false); // Ẩn form đăng ký sau khi đăng ký thành công
            } else {
                JOptionPane.showMessageDialog(null, "Đăng ký không thành công. Vui lòng thử lại!");
            }

            loginStatement.close();
            registerStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return rootPaneCheckingEnabled;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterForm::new);
    }
}