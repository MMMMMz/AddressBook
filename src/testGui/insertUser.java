package testGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class insertUser {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertUser window = new insertUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public insertUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 384, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(10,2));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(Color.RED);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5B66\u53F7    ");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("\u59D3\u540D   ");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_2.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("\u6027\u522B    ");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_3.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		panel_4.add(label_5);
		
		JLabel label_6 = new JLabel("\u5DE5\u4F5C\u5355\u4F4D   ");
		label_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_4.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		textField_3.setColumns(10);
		panel_4.add(textField_3);
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		panel_5.add(label_7);
		
		JLabel label_8 = new JLabel("\u8054\u7CFB\u65B9\u5F0F   ");
		label_8.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_5.add(label_8);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		textField_4.setColumns(10);
		panel_5.add(textField_4);
		
		JPanel panel_6 = new JPanel();
		frame.getContentPane().add(panel_6);
		
		JLabel label_9 = new JLabel("*");
		label_9.setForeground(Color.RED);
		panel_6.add(label_9);
		
		JLabel label_10 = new JLabel("\u90AE\u7BB1    ");
		label_10.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		panel_6.add(label_10);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		textField_5.setColumns(10);
		panel_6.add(textField_5);
		
		JPanel panel_7 = new JPanel();
		frame.getContentPane().add(panel_7);
		
		JPanel panel_8 = new JPanel();
		frame.getContentPane().add(panel_8);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(30,159,255));
		panel_8.add(btnNewButton);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255,87,34));
		panel_8.add(button);
		
		JPanel panel_9 = new JPanel();
		frame.getContentPane().add(panel_9);
	}

}
