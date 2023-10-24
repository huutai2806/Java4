import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Product extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable tableSP;
	DefaultTableModel dfTableModel = new DefaultTableModel(
			null,
			new String[] {
				"Mã HH", "Tên HH", "Giá", "Xuất xứ","Loại hàng"
			}
		);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Product() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(207, 11, 388, 38);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 55, 732, 161);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 sp");
		lblNewLabel_1.setBounds(43, 14, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(121, 11, 239, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(121, 61, 239, 20);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u0110\u01A1n gi\u00E1");
		lblNewLabel_1_1.setBounds(43, 64, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(493, 14, 239, 20);
		panel.add(textField_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("T\u00EAn sp");
		lblNewLabel_1_2.setBounds(415, 17, 46, 14);
		panel.add(lblNewLabel_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(493, 61, 239, 20);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Xu\u1EA5t x\u1EE9 ");
		lblNewLabel_1_3.setBounds(415, 64, 68, 14);
		panel.add(lblNewLabel_1_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(493, 111, 239, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_1_4 = new JLabel("Lo\u1EA1i sp");
		lblNewLabel_1_4.setBounds(415, 114, 46, 14);
		panel.add(lblNewLabel_1_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 227, 732, 176);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnGhi = new JButton("Ghi");
		btnGhi.setBounds(60, 11, 89, 23);
		panel_1.add(btnGhi);
		
		JButton btnKhng = new JButton("Kh\u00F4ng");
		btnKhng.setBounds(214, 11, 89, 23);
		panel_1.add(btnKhng);
		
		JButton btnSa = new JButton("S\u1EEDa ");
		btnSa.setBounds(387, 11, 89, 23);
		panel_1.add(btnSa);
		
		JButton btnXo = new JButton("Xo\u00E1");
		btnXo.setBounds(570, 11, 89, 23);
		panel_1.add(btnXo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 712, 129);
		panel_1.add(scrollPane);
		List<Hanghoa> lst=new ArrayList<Hanghoa>();
		CSDL csdl=new CSDL();
		try {
			lst=csdl.getHanghoa();
			for(Hanghoa hh:lst) {
				Vector value=new Vector();
				value.add(hh.getMhh());
				value.add(hh.getTenhh());
				value.add(hh.getDonggia());
				value.add(hh.getXuatxu());
				value.add(hh.getLoaisp());
				dfTableModel.addRow(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		tableSP = new JTable();
		tableSP.setModel(dfTableModel);
		scrollPane.setViewportView(tableSP);
	}
}
