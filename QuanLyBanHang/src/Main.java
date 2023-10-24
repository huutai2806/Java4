import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmQunLBn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmQunLBn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQunLBn = new JFrame();
		frmQunLBn.setTitle("Qu\u1EA3n l\u00FD b\u00E1n h\u00E0ng");
		frmQunLBn.setBounds(100, 100, 781, 441);
		frmQunLBn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQunLBn.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmQunLBn.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("H\u1EC7 th\u1ED1ng");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmngNhp = new JMenuItem("\u0110\u0103ng nh\u1EADp");
		mnNewMenu.add(mntmngNhp);
		
		JMenuItem mntmngK = new JMenuItem("\u0110\u0103ng Xu\u1EA5t");
		mnNewMenu.add(mntmngK);
		
		JMenuItem mntmThot = new JMenuItem("Tho\u00E1t");
		mnNewMenu.add(mntmThot);
		
		JMenu mnQunL = new JMenu("Qu\u1EA3n l\u00FD");
		menuBar.add(mnQunL);
		
		JMenuItem mntmSnPhm = new JMenuItem("S\u1EA3n ph\u1EA9m");
		mntmSnPhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product frameProduc = new Product();
				frameProduc.setVisible(true);
			}
		});
		mnQunL.add(mntmSnPhm);
		
		JMenuItem mntmNhCungCp = new JMenuItem("Nh\u00E0 cung c\u1EA5p ");
		mnQunL.add(mntmNhCungCp);
		
		JMenuItem mntmKhchHng = new JMenuItem("Kh\u00E1ch h\u00E0ng");
		mnQunL.add(mntmKhchHng);
		
		JMenuItem mntmHonBn = new JMenuItem("Ho\u00E1 \u0111\u01A1n b\u00E1n h\u00E0ng");
		mnQunL.add(mntmHonBn);
	}
}
