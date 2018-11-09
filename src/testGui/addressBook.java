package testGui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.UIManager.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;


import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;


public class addressBook extends JFrame implements ActionListener{

	private static JFrame frmMazhe;
	private JPanel panel_1;
	private JTextPane textPane;
	private JTextField textField;
	private JTextPane textPane_1;
	private JTextField textField_1;
	private JTextPane textPane_2;
	private JTextField textField_2;
	private JButton button;
	private JButton button_1;
	private UserDao user=new UserDao();
	private DbUtil Dbu = new DbUtil();
	private Connection con;
	private JPopupMenu m_popupMenu;
	String[] head;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
    private JTextField id,name,gender,wordAdd,phon,ema;	//用户的信息
    private Font font = new Font("微软雅黑", Font.PLAIN, 18);	//添加统一的字体
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	private JTextField textField8;
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//验证手机号的正则
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";	//验证邮箱的正则
	private DefaultTableModel tableModel;
	private GroupLayout gl_contentPane;
	private JPanel panel;
    private final UserTableModel userTableModel = new UserTableModel();
    private final String SEARCHNAME = "请输入姓名";	//搜索时的默认值
    private final String SEARCHID = "请输入学号";
    private final String SEARCHPHONE = "请输入手机号";
    private JButton button_2;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
				try {
					addressBook window = new addressBook();
					window.frmMazhe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public addressBook() throws Exception {
		con = Dbu.getCon();	//得到一个JDBC的链接
		initialize();
		String className = "javax.swing.plaf.metal.MetalLookAndFeel";	//更换观感
		try { 
			UIManager.setLookAndFeel(className); 
			SwingUtilities.updateComponentTreeUI(this);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMazhe = new JFrame();
		frmMazhe.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		frmMazhe.setBackground(Color.BLACK);
		frmMazhe.setTitle("MaZhe\u7684\u901A\u8BAF\u5F55");	//设置标题
		frmMazhe.getContentPane().setBackground(Color.WHITE);	//设置背景颜色
		frmMazhe.getContentPane().setLayout(new BorderLayout(0, 0));	//设置边界式布局
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);	//设置大画板的背景颜色
		frmMazhe.getContentPane().add(panel, BorderLayout.CENTER);	//将大画板添加到背板的中间
		panel.setLayout(new BorderLayout(0, 0));	//设置大画板为边界式布局
	        
	    scrollPane = new JScrollPane();	//设置一个滚动的Panel用于存放JTable
        panel.add(scrollPane, BorderLayout.CENTER);	//将这个Panel放在背板的中间
        
        table = new JTable();	//创建一个新的table
        try {
			userTableModel.setUsers(user.userList());	//将table的模式设置为自定义的tableModel
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        table.setRowHeight(30);	//设置table的行高
        table.setFont(new Font("微软雅黑", Font.PLAIN, 18));	//设置table的字体
        scrollPane.setViewportView(table);
        table.setModel(userTableModel);
        Color color = new Color(51,102,153);	//设置一个蓝青色的颜色
        Color backColor = new Color(237,247,249);	//设置一个红色的颜色
        table.getTableHeader().setReorderingAllowed(false);	//设置不可滚动
        table.getTableHeader().setResizingAllowed(false);   
        table.getTableHeader().setFont(font);	//设置头的字体
        table.getTableHeader().setForeground(color);	//设置头的字体颜色
        table.getTableHeader().setBackground(backColor);	//设置头的背景颜色
        
        m_popupMenu = new JPopupMenu();	//设置右键的菜单
        
        JMenuItem delMenItem = new JMenuItem();
        JMenuItem updateMenItem = new JMenuItem();
        delMenItem.setText("删除");	//删除
        delMenItem.setFont(font);
        updateMenItem.setText("修改");
        updateMenItem.setFont(font);
        delMenItem.addActionListener(new java.awt.event.ActionListener() {	//给删除事件增加监听器
            public void actionPerformed(ActionEvent evt) {

                int focusedRowIndex =  table.getSelectedRow();
                System.out.println(focusedRowIndex);
                if (focusedRowIndex == -1) {
                    return;
                }
                String userId = table.getValueAt(focusedRowIndex, 0).toString();
                System.out.println(userId);
            	try {
					user.userDelete(con, userId);
					userTableModel.setUsers(user.userList());
            	} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        updateMenItem.addActionListener(new ActionListener() {	//给修改增加监听器
        	public void actionPerformed(ActionEvent e) {
        		int focusedRowIndex =  table.getSelectedRow();
        		textField4 = new JTextField();    			
        		textField6 = new JTextField();
    			textField8 = new JTextField();
    			textField7 = new JTextField();
    			
				JComboBox comboBox=new JComboBox();	//添加一个下拉框
				comboBox.setFont(font);	//设置下拉框的字体
    			comboBox.setBackground(Color.WHITE);	//设置下拉框的背景颜色
    			comboBox.addItem("男");	//为下拉框添加选项
    			comboBox.addItem("女");
    			
				int op = 0;
				
                System.out.println(focusedRowIndex);
                if (focusedRowIndex == -1) {
                    return;
                }
        		JDialog frame = new JDialog();	//构造一个弹出框
    	        frame.setLocation(400, 200);
    	        User nUser;
				try {
					nUser = user.userList().get(focusedRowIndex);
	    			textField4.setText(nUser.getUserName());
	    			if(nUser.getSex().equals("男")) {
	    				op = 0;
	    			} else {
	    				op = 1;
	    			}
	    			comboBox.setSelectedIndex(op);
	    			textField6.setText(nUser.getWorkSddress());
	    			textField8.setText(nUser.getPhone());
	    			textField7.setText(nUser.getMail());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
    	        
    	        frame.setBackground(Color.WHITE);
    	        frame.setTitle("修改用户");
    	        frame.setSize(400,500);
    	        frame.getContentPane().setBackground(Color.WHITE);
    	        frame.getContentPane().setLayout(new GridLayout(10,2));	//设置网格式布局为10行2列
    	        
    	        JPanel panel1 = new JPanel();
    			panel1.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel1);
    			
    			
    			
    			JPanel panel3 = new JPanel();
    			panel3.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel3);

    			
    			JLabel label4 = new JLabel("\u59D3\u540D   ");
    			label4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			panel3.add(label4);
    			
				
    			textField4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			textField4.setColumns(10);
    			panel3.add(textField4);
    			
    			JPanel panel4 = new JPanel();
    			panel4.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel4);
    			
    			
    			JLabel label6 = new JLabel("\u6027\u522B    ");
    			label6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			panel4.add(label6);
    			
    			panel4.add(comboBox);
    			
    			JPanel panel5 = new JPanel();
    			panel5.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel5);
    			

    			
    			JLabel label8 = new JLabel("\u5DE5\u4F5C\u5355\u4F4D   ");
    			label8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			panel5.add(label8);
    			
    			textField6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			textField6.setColumns(10);
    			panel5.add(textField6);
    			
    			JPanel panel_5 = new JPanel();
    			panel_5.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel_5);
    			
    			
    			JLabel label_8 = new JLabel("\u8054\u7CFB\u65B9\u5F0F   ");
    			label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			panel_5.add(label_8);
    			
    			textField8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			textField8.setColumns(10);
    			panel_5.add(textField8);
    			
    			JPanel panel6 = new JPanel();
    			panel6.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel6);

    			
    			JLabel label10 = new JLabel("\u90AE\u7BB1    ");
    			label10.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			panel6.add(label10);
    			
    			textField7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			textField7.setColumns(12);
    			panel6.add(textField7);
    			

    			JPanel panel_7 = new JPanel();
    			panel_7.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel_7);
    			
    			JPanel panel_8 = new JPanel();
    			panel_8.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel_8);
    			
    			JButton confirmButton = new JButton("\u786E\u5B9A");
    			confirmButton.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent evt) {
    					User newUser = new User();
    					int focusedRowIndex =  table.getSelectedRow();
    	                if (focusedRowIndex == -1) {	//获取选中的行数
    	                    return;
    	                }
    					String userName = textField4.getText();	//获取输入的姓名
    					String gender = comboBox.getSelectedItem().toString();	//获取输入的性别
    					String workAdd = textField6.getText();	//获取输入的工作地址
    					String phone = textField8.getText();	//获取输入的电话
    					String mail = textField7.getText();	//获取输入的邮箱
    					try {
							User nowUser = user.userList().get(focusedRowIndex);
							String userId = nowUser.getUserId();
							newUser.setUserId(userId);
							if(!userName.equals("")) {
    							newUser.setUserName(userName);	//判断用户名是否是新输入的
    						} else {
    							newUser.setUserName(nowUser.getUserName());
    						}
    						
    						if(!gender.equals("")) {
    							newUser.setSex(gender);	//判断用户性别是否是新输入的
    						} else {
    							newUser.setSex(nowUser.getSex());
    						}
    						
    						if(!workAdd.equals("")) {
    							newUser.setWorkSddress(workAdd);	//判断用户工作地址是否是新输入的
    						} else {
    							newUser.setWorkSddress(nowUser.getWorkSddress());
    						}
    						
    						if(!phone.equals("")) {
    							if(Pattern.matches(REGEX_MOBILE, phone)) {
    	    						newUser.setPhone(phone);
    	    					} else {
    	    						JOptionPane.showMessageDialog(null, "您输入的手机号长度有错误!", "错误", JOptionPane.WARNING_MESSAGE);
    	    					}
    						} else {
    							newUser.setPhone(nowUser.getPhone());
    						}
    						
    						if(!mail.equals("")) {
    							if(Pattern.matches(REGEX_EMAIL, mail)) {
    	    						newUser.setMail(mail);
    	    					} else {
    	    						JOptionPane.showMessageDialog(null, "您输入的邮箱格式有误!", "错误", JOptionPane.WARNING_MESSAGE);
    	    					}
    						} else {
    							newUser.setMail(nowUser.getMail());
    						}
    						
    						user.userModify(con, newUser);
    						JOptionPane.showMessageDialog(null, "恭喜您!修改成功", "恭喜", JOptionPane.CLOSED_OPTION);
    						userTableModel.setUsers(user.userList());
    						frame.dispose();
						} catch (Exception e1) {
    						JOptionPane.showMessageDialog(null, "系统出现错误!", "失败", JOptionPane.CLOSED_OPTION);
						}

    				}
    			});
    			confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			confirmButton.setForeground(Color.WHITE);
    			confirmButton.setBackground(new Color(30,159,255));
    			panel_8.add(confirmButton);
    			
    			JButton cancleButton = new JButton("\u53D6\u6D88");
    			cancleButton.addActionListener(new ActionListener() {
    				
    				@Override
    				public void actionPerformed(ActionEvent arg0) {
    					scrollPane.updateUI();
    					frame.dispose();
    				}
    			});
    			cancleButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    			cancleButton.setForeground(Color.WHITE);
    			cancleButton.setBackground(new Color(255,87,34));
    			panel_8.add(cancleButton);
    			
    			JPanel panel_9 = new JPanel();
    			panel_9.setBackground(Color.WHITE);
    			frame.getContentPane().add(panel_9);
    			
    	        frame.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);    
    	        frame.setVisible(true);
    		}
			}
		);
        m_popupMenu.add(delMenItem);
        m_popupMenu.add(updateMenItem);
        table.addMouseListener(new MouseListener() {
			
        	public void mouseReleased(MouseEvent e) {
        		userTableModel.fireTableDataChanged();
        		if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //获取选中的行
                    int focusedRowIndex = table.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    m_popupMenu.show(table, e.getX(), e.getY());
                }

			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        scrollPane.setViewportView(table);
        
        gl_contentPane = new GroupLayout(panel);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
                    .addGap(66))
        );
        panel.setLayout(gl_contentPane);
        
        
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		frmMazhe.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textPane.setEditable(false);
		textPane.setText("\u59D3\u540D");
		panel_1.add(textPane);
		
		textField = new JTextField();
		textField.addFocusListener(new JTextFieldHintListener("请输入姓名", textField));	//����ȡĬ�ϵ�ֵ
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setColumns(15);
		panel_1.add(textField);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textPane_1.setText("\u5B66\u53F7");
		textPane_1.setEditable(false);
		panel_1.add(textPane_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_1.addFocusListener(new JTextFieldHintListener("请输入学号", textField_1));
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setColumns(15);
		panel_1.add(textField_1);
		
		textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textPane_2.setEditable(false);
		textPane_2.setText("\u7535\u8BDD");
		panel_1.add(textPane_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_2.addFocusListener(new JTextFieldHintListener("请输入手机号", textField_2));
		textField_2.setForeground(Color.LIGHT_GRAY);
		textField_2.setColumns(15);
		panel_1.add(textField_2);
		
		button = new JButton("\u67E5\u8BE2");
		button.addActionListener(this);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		button.setBackground(new Color(0, 150, 136));
		panel_1.add(button);
		
		button_1 = new JButton("\u65B0\u589E");
		button_1.addActionListener(this);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		button_1.setBackground(new Color(0, 150, 136));
		panel_1.add(button_1);
		
		button_2 = new JButton("统计");
		button_2.setBackground(new Color(0, 150, 136));
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		button_2.addActionListener(this);
		panel_1.add(button_2);
		
		frmMazhe.setBounds(100, 100, 1253, 572);
		frmMazhe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_1) {
			JDialog frame = new JDialog();	//构造一个弹出框
	        frame.setBounds(//设置弹出框的边界
	                new Rectangle(
	                        (int) this.getBounds().getX() + 400,
	                        (int) this.getBounds().getY() + 150, 
	                        (int) this.getBounds().getWidth(), 
	                        (int) this.getBounds().getHeight()
	                )
	            );	        
	        
	        frame.setBackground(Color.WHITE);
	        frame.setTitle("新增用户");
	        frame.setSize(400,500);
	        frame.getContentPane().setBackground(Color.WHITE);
	        frame.getContentPane().setLayout(new GridLayout(10,2));	//设置网格式布局为10行2列
	        
	        JPanel panel1 = new JPanel();
			panel1.setBackground(Color.WHITE);
			frame.getContentPane().add(panel1);
			
			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.WHITE);
			frame.getContentPane().add(panel2);

			JLabel lable1 = new JLabel("*");
			lable1.setForeground(Color.RED);
			panel2.add(lable1);
			
			JLabel label2 = new JLabel("\u5B66\u53F7    ");
			label2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel2.add(label2);
			
			textField3 = new JTextField();
			textField3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel2.add(textField3);
			textField3.setColumns(10);
			
			
			JPanel panel3 = new JPanel();
			panel3.setBackground(Color.WHITE);
			frame.getContentPane().add(panel3);
			
			JLabel label3 = new JLabel("*");
			label3.setForeground(Color.RED);
			panel3.add(label3);
			
			JLabel label4 = new JLabel("\u59D3\u540D   ");
			label4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel3.add(label4);
			
			textField4 = new JTextField();
			textField4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			textField4.setColumns(10);
			panel3.add(textField4);
			
			JPanel panel4 = new JPanel();
			panel4.setBackground(Color.WHITE);
			frame.getContentPane().add(panel4);
			
			JLabel label5 = new JLabel("*");
			label5.setForeground(Color.RED);
			panel4.add(label5);
			
			JLabel label6 = new JLabel("\u6027\u522B              ");
			label6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel4.add(label6);
			
			JComboBox comboBox=new JComboBox();
			comboBox.setFont(font);
			comboBox.setBackground(Color.WHITE);
			comboBox.addItem("男");
			comboBox.addItem("女");
			panel4.add(comboBox);

			
			JPanel panel5 = new JPanel();
			panel5.setBackground(Color.WHITE);
			frame.getContentPane().add(panel5);
			
			JLabel label8 = new JLabel(" \u5DE5\u4F5C\u5355\u4F4D   ");
			label8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel5.add(label8);
			
			textField6 = new JTextField();
			textField6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			textField6.setColumns(10);
			panel5.add(textField6);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			frame.getContentPane().add(panel_5);
			
			JLabel label_7 = new JLabel("*");
			label_7.setForeground(Color.RED);
			panel_5.add(label_7);
			
			JLabel label_8 = new JLabel("\u8054\u7CFB\u65B9\u5F0F   ");
			label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel_5.add(label_8);
			
			textField8 = new JTextField();
			textField8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			textField8.setColumns(10);
			panel_5.add(textField8);
			
			JPanel panel6 = new JPanel();
			panel6.setBackground(Color.WHITE);
			frame.getContentPane().add(panel6);
			
			JLabel label9 = new JLabel("*");
			label9.setForeground(Color.RED);
			panel6.add(label9);
			
			JLabel label10 = new JLabel("\u90AE\u7BB1    ");
			label10.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			panel6.add(label10);
			
			textField7 = new JTextField();
			textField7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			textField7.setColumns(10);
			panel6.add(textField7);
			

			JPanel panel_7 = new JPanel();
			panel_7.setBackground(Color.WHITE);
			frame.getContentPane().add(panel_7);
			
			JPanel panel_8 = new JPanel();
			panel_8.setBackground(Color.WHITE);
			frame.getContentPane().add(panel_8);
			
			JButton confirmButton = new JButton("\u786E\u5B9A");
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					User newUser = new User();
					String userId = textField3.getText();
					String userName = textField4.getText();
					newUser.setUserName(userName);
					String gender = comboBox.getSelectedItem().toString();
					newUser.setSex(gender);
					String workAdd = textField6.getText();
					newUser.setWorkSddress(workAdd);
					String phone = textField8.getText();
					String mail = textField7.getText();

					
					if(userId.length() == 6) {
						newUser.setUserId(userId);
						
						if(Pattern.matches(REGEX_MOBILE, phone)) {
							newUser.setPhone(phone);
							
							if(Pattern.matches(REGEX_EMAIL, mail)) {
								newUser.setMail(mail);
							} else {
								JOptionPane.showMessageDialog(null, "您输入的邮箱格式有误!", "错误", JOptionPane.WARNING_MESSAGE);
								return ;
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "您输入的手机号长度有错误!", "错误", JOptionPane.WARNING_MESSAGE);
							return ;
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "学号必须为六位!", "错误", JOptionPane.ERROR_MESSAGE);
						return ;
					}
					
					try {
						user.userAdd(con, newUser);
						JOptionPane.showMessageDialog(null, "恭喜您!添加成功", "恭喜", JOptionPane.CLOSED_OPTION);
						userTableModel.setUsers(user.userList());
						frame.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "学号不能重复或出现系统错误!", "失败", JOptionPane.CLOSED_OPTION);
					}

				}
			});
			confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			confirmButton.setForeground(Color.WHITE);
			confirmButton.setBackground(new Color(30,159,255));
			panel_8.add(confirmButton);
			
			JButton cancleButton = new JButton("\u53D6\u6D88");
			cancleButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					scrollPane.updateUI();
					frame.dispose();
				}
			});
			cancleButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			cancleButton.setForeground(Color.WHITE);
			cancleButton.setBackground(new Color(255,87,34));
			panel_8.add(cancleButton);
			
			JPanel panel_9 = new JPanel();
			panel_9.setBackground(Color.WHITE);
			frame.getContentPane().add(panel_9);
			
	        frame.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);    
	        frame.setVisible(true);
		}
		
		if(e.getSource() == button) {
			String name = textField.getText();
			String id = textField_1.getText();
			String num = textField_2.getText();
			try {
				if(!name.equals(SEARCHNAME) || name.equals("")) {	//通过姓名模糊查询用户信息
					List<User> getUser = user.userSearchByUserName(name);
					userTableModel.setUsers(getUser);
					return ;
				} else {
					userTableModel.setUsers(user.userList());
					if(!id.equals(SEARCHID) || id.equals("")) {	//通过学号模糊查询用户信息
						List<User> getUser = user.userSearchByUserId(id);
						userTableModel.setUsers(getUser);
						return ;
					} else {
						userTableModel.setUsers(user.userList());
						if(!num.equals(SEARCHPHONE) || num.equals("")) {	//通过手机号模糊查询用户信息
							List<User> getUser = user.userSearchByUserPhoneNum(num);
							userTableModel.setUsers(getUser);
							return ;
						} else {
							userTableModel.setUsers(user.userList());
							return ;
						}
					}
				} 
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == button_2) {
			try {
				List<User> list = user.userList();
				int sum = list.size();
				int countBoy = 0;
				int countGirl = 0;
				for(int i = 0 ; i < sum ; i++)
				{
					if(list.get(i).getSex().equals("男")) {
						countBoy++;
					}else if(list.get(i).getSex().equals("女")) {
						countGirl++;
					}
				}
				JOptionPane.showMessageDialog(null, "通讯录中共有:" + sum +"人\n" + "男生有:" + countBoy + "人\n" + "女生有:" + countGirl + "人", "统计", JOptionPane.CLOSED_OPTION);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "系统出现错误!", "失败", JOptionPane.CLOSED_OPTION);
			}
		}
	}
}


