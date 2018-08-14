package durokovic.ljetnizadatak.view;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	private String url;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		url = "https://github.com/mdurokov/Edunova_ljetni_Eclipse";
		setTitle("WCA - World Cube Assocciation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton continentsBtn = new JButton("Continents");
		continentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContinentFrame continentFrame = new ContinentFrame();
		        continentFrame.setVisible(true);      
			}
		});
		continentsBtn.setBounds(10, 11, 398, 23);
		contentPane.add(continentsBtn);
		
		JButton eventsBtn = new JButton("Events");
		eventsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventFrame eventFrame = new EventFrame();
		        eventFrame.setVisible(true);      
			}
		});
		eventsBtn.setBounds(10, 45, 398, 23);
		contentPane.add(eventsBtn);
		
		JButton formatsBtn = new JButton("Formats");
		formatsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormatFrame formatFrame = new FormatFrame();
		        formatFrame.setVisible(true);      
			}
		});
		formatsBtn.setBounds(10, 79, 398, 23);
		contentPane.add(formatsBtn);
		
		JButton roundTypesBtn = new JButton("Round Types");
		roundTypesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoundTypeFrame roundTypeFrame = new RoundTypeFrame();
		        roundTypeFrame.setVisible(true);      
			}
		});
		roundTypesBtn.setBounds(10, 113, 398, 23);
		contentPane.add(roundTypesBtn);
		
		JButton eraBtn = new JButton("ERA Diagram");
		eraBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EraFrame era = new EraFrame();
				era.setVisible(true);
			}
		});
		eraBtn.setBounds(10, 147, 398, 23);
		contentPane.add(eraBtn);
		
		JLabel linkToCodeLbl = new JLabel("Link to Code");
		linkToCodeLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
		            Desktop desktop = java.awt.Desktop.getDesktop();
		            URI open = new URI(url);
		            desktop.browse(open);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		});
		linkToCodeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		linkToCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		linkToCodeLbl.setIcon(new ImageIcon(MainFrame.class.getResource("/img/git.png")));
		linkToCodeLbl.setBounds(10, 181, 199, 35);
		linkToCodeLbl.setIconTextGap(20);
		linkToCodeLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(linkToCodeLbl);
		
		
	}
}
