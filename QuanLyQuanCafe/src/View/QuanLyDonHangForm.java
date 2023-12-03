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

public class QuanLyDonHangForm extends JFrame {
    private JTextField txtMaDonHang, txtThoiGian, txtTrangThai;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel model;

    public QuanLyDonHangForm() {
        setTitle("Quản Lý Đơn Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        txtMaDonHang = new JTextField(10);
        txtThoiGian = new JTextField(10);
        txtTrangThai = new JTextField(10);

        inputPanel.add(new JLabel("Mã Đơn Hàng:"));
        inputPanel.add(txtMaDonHang);
        inputPanel.add(new JLabel("Thời Gian:"));
        inputPanel.add(txtThoiGian);
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

        // Tạo JTable để hiển thị dữ liệu đơn hàng
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Mã Đơn Hàng", "Thời Gian", "Trạng Thái"});
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
                addDonHang();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                // Lấy dữ liệu từ hàng được chọn và hiển thị lên trên các trường nhập liệu
                displaySelectedDonHang(selectedRowIndex);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDonHang();
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteDonHang();
            }
        });
    }

    // Phương thức để load dữ liệu từ CSDL và hiển thị lên bảng
    private void loadDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DonHang");

            while (resultSet.next()) {
                String maDonHang = resultSet.getString("MaDonHang");
                String thoiGian = resultSet.getString("ThoiGian");
                String trangThai = resultSet.getString("TrangThai");

                model.addRow(new Object[]{maDonHang, thoiGian, trangThai});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thêm đơn hàng vào CSDL và cập nhật bảng
    private void addDonHang() {
        String maDonHang = txtMaDonHang.getText();
        String thoiGian = txtThoiGian.getText();
        String trangThai = txtTrangThai.getText();

        try {
        	 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            String query = "INSERT INTO DonHang (MaDonHang, ThoiGian, TrangThai) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maDonHang);
            preparedStatement.setString(2, thoiGian);
            preparedStatement.setString(3, trangThai);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                model.addRow(new Object[]{maDonHang, thoiGian, trangThai});
                clearFields();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để hiển thị dữ liệu của đơn hàng được chọn lên trên các trường nhập liệu
    private void displaySelectedDonHang(int selectedRowIndex) {
        if (selectedRowIndex != -1) {
            txtMaDonHang.setText(table.getValueAt(selectedRowIndex, 0).toString());
            txtThoiGian.setText(table.getValueAt(selectedRowIndex, 1).toString());
            txtTrangThai.setText(table.getValueAt(selectedRowIndex, 2).toString());
        }
    }

    // Phương thức để cập nhật thông tin của đơn hàng và cập nhật bảng
    private void updateDonHang() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maDonHang = txtMaDonHang.getText();
            String thoiGian = txtThoiGian.getText();
            String trangThai = txtTrangThai.getText();

            model.setValueAt(maDonHang, selectedRowIndex, 0);
            model.setValueAt(thoiGian, selectedRowIndex, 1);
            model.setValueAt(trangThai, selectedRowIndex, 2);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "UPDATE DonHang SET ThoiGian=?, TrangThai=? WHERE MaDonHang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, thoiGian);
                preparedStatement.setString(2, trangThai);
                preparedStatement.setString(3, maDonHang);

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

    // Phương thức để xóa đơn hàng khỏi CSDL và cập nhật bảng
    private void deleteDonHang() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maDonHang = txtMaDonHang.getText();

            model.removeRow(selectedRowIndex);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "DELETE FROM DonHang WHERE MaDonHang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, maDonHang);

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
        txtMaDonHang.setText("");
        txtThoiGian.setText("");
        txtTrangThai.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyDonHangForm::new);
    }
}