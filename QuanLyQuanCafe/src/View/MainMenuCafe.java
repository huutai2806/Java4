package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuCafe extends JFrame {
    public MainMenuCafe() {
        setTitle("Quản lý Cafe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu quanLyMenu = new JMenu("Quản lý");

        JMenuItem menuMonAn = new JMenuItem("Món Ăn");
        menuMonAn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi chọn menu sản phẩm
                // Ví dụ: Hiển thị form QuanLySanPham
            	QuanLyMonAnForm quanLyMonAnForm = new QuanLyMonAnForm();
            	quanLyMonAnForm.setVisible(true);
            }
        });
        quanLyMenu.add(menuMonAn);

        JMenuItem menuDonHang = new JMenuItem("Đơn hàng");
        menuDonHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi chọn menu đơn hàng
                // Ví dụ: Hiển thị form QuanLyDonHang
                QuanLyDonHangForm quanLyDonHangForm = new QuanLyDonHangForm();
                quanLyDonHangForm.setVisible(true);
            }
        });
        quanLyMenu.add(menuDonHang);
        JMenuItem menuBan = new JMenuItem("Bàn");
        menuBan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi chọn menu sản phẩm
                // Ví dụ: Hiển thị form QuanLySanPham
            	QuanLyBanForm quanLyBanForm = new QuanLyBanForm();
            	quanLyBanForm.setVisible(true);
            }
        });
        quanLyMenu.add(menuBan);
        JMenuItem menuCTDH = new JMenuItem("Chi Tiết Đơn Hàng");
        menuCTDH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi chọn menu sản phẩm
                // Ví dụ: Hiển thị form QuanLySanPham
            	QuanLyCTDHForm quanLyCTDHForm = new QuanLyCTDHForm();
            	quanLyCTDHForm.setVisible(true);
            }
        });
        quanLyMenu.add(menuCTDH);
        // Thêm các menu vào thanh menuBar
        menuBar.add(quanLyMenu);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuCafe::new);
    }
}
