package com.wave.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wave.dao.ItemDao;
import com.wave.entity.Item;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("账号");
		label.setBounds(54, 87, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(54, 141, 54, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(135, 84, 125, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 138, 125, 18);
		contentPane.add(passwordField);
		
		JButton button = new JButton("登入");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//获取用户名和密码
				String username = textField.getText();
				String password = passwordField.getText();
				//为了简单直接把账号密码写死
				if(username.equals("admin") && password.equals("admin")) {
					//登入成功提示
					JOptionPane.showMessageDialog(LoginFrame.this, "登入成功！");
					//然后创建商品管理界面
					ItemFrame itemFrame = new ItemFrame();
					//显示这个界面
					itemFrame.setVisible(true);
					//关闭登入界面
					LoginFrame.this.dispose();
				}else {
					JOptionPane.showMessageDialog(LoginFrame.this, "登入失败！");
				}
			}
		});
		button.setBounds(47, 197, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame.this.dispose();
			}
		});
		button_1.setBounds(191, 197, 93, 23);
		contentPane.add(button_1);
	}
}
