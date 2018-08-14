package durokovic.ljetnizadatak.view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EraFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EraFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 749, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 743, 524);
		contentPane.add(scrollPane);
		
		JLabel eraLbl = new JLabel("");
		eraLbl.setIcon(new ImageIcon(EraFrame.class.getResource("/img/wca_era.png")));
		scrollPane.setViewportView(eraLbl);
	}
}
