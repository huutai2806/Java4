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

public class QuanLyMonAnForm extends JFrame {
    private JTextField txtMaMon, txtTenMon, txtLoaiMon, txtGia;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel model;

    public QuanLyMonAnForm() {
        setTitle("Quản Lý Sản Phẩm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        txtMaMon = new JTextField(10);
        txtTenMon = new JTextField(10);
        txtLoaiMon = new JTextField(10);
        txtGia = new JTextField(10);

        inputPanel.add(new JLabel("Mã Món:"));
        inputPanel.add(txtMaMon);
        inputPanel.add(new JLabel("Tên Món:"));
        inputPanel.add(txtTenMon);
        inputPanel.add(new JLabel("Loại Món:"));
        inputPanel.add(txtLoaiMon);
        inputPanel.add(new JLabel("Giá:"));
        inputPanel.add(txtGia);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tạo JTable để hiển thị dữ liệu sản phẩm
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Mã Món", "Tên Món", "Loại Món", "Giá"});
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
                addSanPham();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                // Lấy dữ liệu từ hàng được chọn và hiển thị lên trên các trường nhập liệu
                displaySelectedSanPham(selectedRowIndex);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSanPham();
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSanPham();
            }
        });
    }

    // Phương thức để load dữ liệu từ CSDL và hiển thị lên bảng
    private void loadDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MonAn");

            while (resultSet.next()) {
                String maMon = resultSet.getString("MaMon");
                String tenMon = resultSet.getString("TenMon");
                String loaiMon = resultSet.getString("LoaiMon");
                String gia = resultSet.getString("Gia");

                model.addRow(new Object[]{maMon, tenMon, loaiMon, gia});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thêm sản phẩm vào CSDL và cập nhật bảng
    private void addSanPham() {
        String maMon = txtMaMon.getText();
        String tenMon = txtTenMon.getText();
        String loaiMon = txtLoaiMon.getText();
        String gia = txtGia.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            String query = "INSERT INTO MonAn (MaMon, TenMon, LoaiMon, Gia) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maMon);
            preparedStatement.setString(2, tenMon);
            preparedStatement.setString(3, loaiMon);
            preparedStatement.setString(4, gia);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                model.addRow(new Object[]{maMon, tenMon, loaiMon, gia});
                clearFields();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để hiển thị dữ liệu của sản phẩm được chọn lên trên các trường nhập liệu
    private void displaySelectedSanPham(int selectedRowIndex) {
        txtMaMon.setText(table.getValueAt(selectedRowIndex, 0).toString());
        txtTenMon.setText(table.getValueAt(selectedRowIndex, 1).toString());
        txtLoaiMon.setText(table.getValueAt(selectedRowIndex, 2).toString());
        txtGia.setText(table.getValueAt(selectedRowIndex, 3).toString());
    }

    // Phương thức để cập nhật sản phẩm trong CSDL và cập nhật bảng
    private void updateSanPham() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maMon = txtMaMon.getText();
            String tenMon = txtTenMon.getText();
            String loaiMon = txtLoaiMon.getText();
            String gia = txtGia.getText();

            model.setValueAt(maMon, selectedRowIndex, 0);
            model.setValueAt(tenMon, selectedRowIndex, 1);
            model.setValueAt(loaiMon, selectedRowIndex, 2);
            model.setValueAt(gia, selectedRowIndex, 3);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "UPDATE MonAn SET TenMon=?, LoaiMon=?, Gia=? WHERE MaMon=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, tenMon);
                preparedStatement.setString(2, loaiMon);
                preparedStatement.setString(3, gia);
                preparedStatement.setString(4, maMon);

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

    // Phương thức để xóa sản phẩm khỏi CSDL và cập nhật bảng
    private void deleteSanPham() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String maMon = txtMaMon.getText();

            model.removeRow(selectedRowIndex);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "DELETE FROM MonAn WHERE MaMon=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, maMon);

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
        txtMaMon.setText("");
        txtTenMon.setText("");
        txtLoaiMon.setText("");
        txtGia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyMonAnForm::new);
    }
}