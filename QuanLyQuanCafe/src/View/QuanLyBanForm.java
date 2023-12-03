package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QuanLyBanForm extends JFrame {
    private JTextField txtMaBan, txtTenBan, txtTrangThai;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel model;

    public QuanLyBanForm() {
        setTitle("Quản Lý Bàn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        txtMaBan = new JTextField(10);
        txtTenBan = new JTextField(10);
        txtTrangThai = new JTextField(10);

        inputPanel.add(new JLabel("Mã Bàn:"));
        inputPanel.add(txtMaBan);
        inputPanel.add(new JLabel("Tên Bàn:"));
        inputPanel.add(txtTenBan);
        inputPanel.add(new JLabel("Trạng Thái:"));
        inputPanel.add(txtTrangThai);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tạo JTable để hiển thị dữ liệu bàn
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Mã Bàn", "Tên Bàn", "Trạng Thái"});
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Load dữ liệu từ CSDL khi khởi động form
        loadDataFromDatabase();

        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBan();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                // Lấy dữ liệu từ hàng được chọn và hiển thị lên trên các trường nhập liệu
                displaySelectedBan(selectedRowIndex);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBan();
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBan();
            }
        });
    }

    // Phương thức để load dữ liệu từ CSDL và hiển thị lên bảng
    private void loadDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ban");

            while (resultSet.next()) {
                String maBan = resultSet.getString("MaBan");
                String tenBan = resultSet.getString("TenBan");
                String trangThai = resultSet.getString("TrangThai");

                model.addRow(new Object[]{maBan, tenBan, trangThai});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thêm bàn vào CSDL và cập nhật bảng
    private void addBan() {
        String maBan = txtMaBan.getText();
        String tenBan = txtTenBan.getText();
        String trangThai = txtTrangThai.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            String query = "INSERT INTO Ban (MaBan, TenBan, TrangThai) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maBan);
            preparedStatement.setString(2, tenBan);
            preparedStatement.setString(3, trangThai);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                model.addRow(new Object[]{maBan, tenBan, trangThai});
                clearFields();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để hiển thị dữ liệu của bàn được chọn lên trên các trường nhập liệu
    private void displaySelectedBan(int selectedRowIndex) {
        txtMaBan.setText(table.getValueAt(selectedRowIndex, 0).toString());
        txtTenBan.setText(table.getValueAt(selectedRowIndex, 1).toString());
        txtTrangThai.setText(table.getValueAt(selectedRowIndex, 2).toString());
    }

    // Phương thức để cập nhật thông tin của bàn và cập nhật bảng
    private void updateBan() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maBan = txtMaBan.getText();
            String tenBan = txtTenBan.getText();
            String trangThai = txtTrangThai.getText();

            model.setValueAt(maBan, selectedRowIndex, 0);
            model.setValueAt(tenBan, selectedRowIndex, 1);
            model.setValueAt(trangThai, selectedRowIndex, 2);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "UPDATE Ban SET TenBan=?, TrangThai=? WHERE MaBan=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, tenBan);
                preparedStatement.setString(2, trangThai);
                preparedStatement.setString(3, maBan);

                int rowsAffected = preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức để xóa bàn khỏi CSDL và cập nhật bảng
    private void deleteBan() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maBan = txtMaBan.getText();

            model.removeRow(selectedRowIndex);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "DELETE FROM Ban WHERE MaBan=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, maBan);

                int rowsAffected = preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức để xóa dữ liệu trên các trường nhập liệu
    private void clearFields() {
        txtMaBan.setText("");
        txtTenBan.setText("");
        txtTrangThai.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyBanForm::new);
    }
}
