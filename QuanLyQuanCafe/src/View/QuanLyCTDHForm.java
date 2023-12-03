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

public class QuanLyCTDHForm extends JFrame {
    private JTextField txtID, txtMaDonHang, txtMaMon, txtSoLuong, txtGia;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel model;

    public QuanLyCTDHForm() {
        setTitle("Quản Lý Chi Tiết Đơn Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        txtID = new JTextField(10);
        txtMaDonHang = new JTextField(10);
        txtMaMon = new JTextField(10);
        txtSoLuong = new JTextField(10);
        txtGia = new JTextField(10);

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(txtID);
        inputPanel.add(new JLabel("Mã Đơn Hàng:"));
        inputPanel.add(txtMaDonHang);
        inputPanel.add(new JLabel("Mã Món:"));
        inputPanel.add(txtMaMon);
        inputPanel.add(new JLabel("Số Lượng:"));
        inputPanel.add(txtSoLuong);
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

        // Tạo JTable để hiển thị dữ liệu chi tiết đơn hàng
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"ID", "Mã Đơn Hàng", "Mã Món", "Số Lượng", "Giá"});
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
                addCTDH();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                // Lấy dữ liệu từ hàng được chọn và hiển thị lên trên các trường nhập liệu
                displaySelectedCTDH(selectedRowIndex);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCTDH();
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCTDH();
            }
        });
    }

    // Phương thức để load dữ liệu từ CSDL và hiển thị lên bảng
    private void loadDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ChiTietDonHang");

            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String maDonHang = resultSet.getString("MaDonHang");
                String maMon = resultSet.getString("MaMon");
                String soLuong = resultSet.getString("SoLuong");
                String gia = resultSet.getString("Gia");

                model.addRow(new Object[]{ID, maDonHang, maMon, soLuong, gia});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thêm chi tiết đơn hàng vào CSDL và cập nhật bảng
    private void addCTDH() {
        String ID = txtID.getText();
        String maDonHang = txtMaDonHang.getText();
        String maMon = txtMaMon.getText();
        String soLuong = txtSoLuong.getText();
        String gia = txtGia.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
            String query = "INSERT INTO ChiTietDonHang (ID, MaDonHang, MaMon, SoLuong, Gia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, maDonHang);
            preparedStatement.setString(3, maMon);
            preparedStatement.setString(4, soLuong);
            preparedStatement.setString(5, gia);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                model.addRow(new Object[]{ID, maDonHang, maMon, soLuong, gia});
                clearFields();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để hiển thị dữ liệu của chi tiết đơn hàng được chọn lên trên các trường nhập liệu
    private void displaySelectedCTDH(int selectedRowIndex) {
        txtID.setText(table.getValueAt(selectedRowIndex, 0).toString());
        txtMaDonHang.setText(table.getValueAt(selectedRowIndex, 1).toString());
        txtMaMon.setText(table.getValueAt(selectedRowIndex, 2).toString());
        txtSoLuong.setText(table.getValueAt(selectedRowIndex, 3).toString());
        txtGia.setText(table.getValueAt(selectedRowIndex, 4).toString());
    }

    // Phương thức để cập nhật thông tin của chi tiết đơn hàng và cập nhật bảng
    private void updateCTDH() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String ID = txtID.getText();
            String maDonHang = txtMaDonHang.getText();
            String maMon = txtMaMon.getText();
            String soLuong = txtSoLuong.getText();
            String gia = txtGia.getText();

            model.setValueAt(ID, selectedRowIndex, 0);
            model.setValueAt(maDonHang, selectedRowIndex, 1);
            model.setValueAt(maMon, selectedRowIndex, 2);
            model.setValueAt(soLuong, selectedRowIndex, 3);
            model.setValueAt(gia, selectedRowIndex, 4);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "UPDATE ChiTietDonHang SET MaDonHang=?, MaMon=?, SoLuong=?, Gia=? WHERE ID=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, maDonHang);
                preparedStatement.setString(2, maMon);
                preparedStatement.setString(3, soLuong);
                preparedStatement.setString(4, gia);
                preparedStatement.setString(5, ID);

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

    // Phương thức để xóa chi tiết đơn hàng khỏi CSDL và cập nhật bảng
    private void deleteCTDH() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String ID = txtID.getText();

            model.removeRow(selectedRowIndex);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcf", "root", "");
                String query = "DELETE FROM ChiTietDonHang WHERE ID=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ID);

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
        txtID.setText("");
        txtMaDonHang.setText("");
        txtMaMon.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyCTDHForm::new);
    }
}