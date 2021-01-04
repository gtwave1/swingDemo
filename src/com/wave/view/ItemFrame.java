package com.wave.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wave.dao.ItemDao;
import com.wave.entity.Item;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ItemFrame extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private  JTable table = null;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private ItemDao itemDao = new ItemDao();
	
	//表格头部
	private String head[] = {"商品id","商品名称","商品数量","商品价格"};
	private Vector<String> header = new Vector<String>();
	//数据
	private Vector<Vector<String>> data = new Vector<Vector<String>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemFrame frame = new ItemFrame();
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
	public ItemFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 476, 193);
		
		
		//向版面添加头部
		for(int i = 0;i < head.length;i++) {
			header.add(head[i]);
		}
		//把数据查出来
		data = itemDao.selectAll();
		table = new JTable(data,header);
		
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 231, 456, 154);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品id");
		lblNewLabel.setBounds(31, 10, 54, 15);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("商品名称");
		label.setBounds(112, 10, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("商品数量");
		label_1.setBounds(198, 10, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("商品价格");
		label_2.setBounds(290, 10, 54, 15);
		panel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 35, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(186, 35, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(278, 35, 66, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Item item = new Item();
				item.setName(textField_1.getText());
				item.setNum(Integer.parseInt(textField_2.getText()));
				//输入的是浮点数，把它乘以100变成一个整型
				item.setPrice((int)(Double.parseDouble(textField_3.getText()) * 100));
				//添加进数据库
				int id = itemDao.add(item);
				//创建一个vector
				Vector<String> row = new Vector<>();
				row.add(id + "");
				row.add(item.getName());
				row.add(item.getNum() + "");
				row.add(Double.parseDouble(textField_3.getText()) + "");
				//把这个vector的数据加入到data里面
				data.add(row);
				//更新table
				table.updateUI();
			}
		});
		btnNewButton.setBounds(354, 34, 93, 23);
		panel.add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setBounds(100, 68, 66, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(186, 68, 66, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(278, 68, 66, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//获取选择的那一行
				int selectIndex = table.getSelectedRow();
				//如果selectIndex为-1表示没有选择
				if(selectIndex == -1) {
					JOptionPane.showMessageDialog(ItemFrame.this, "还没有选择要修改的是哪一行！");
				}else {
					//获取要修改的值的id
					int id = Integer.parseInt(data.get(selectIndex).get(0));
					Item item = new Item();
					item.setName(textField_4.getText());
					item.setNum(Integer.parseInt(textField_5.getText()));
					item.setPrice((int)(Double.parseDouble(textField_6.getText()) * 100));
					//更新数据库数据
					itemDao.update(id, item);
					//更新界面数据
					data.get(selectIndex).set(1, item.getName());
					data.get(selectIndex).set(2, item.getNum() + "");
					data.get(selectIndex).set(3, (item.getPrice() / 100.0) + "");
					//更新界面
					table.updateUI();
					//提示一下修改成功
					JOptionPane.showMessageDialog(ItemFrame.this, "修改成功", "提示",1);
				}
			}
		});
		button.setBounds(354, 67, 93, 23);
		panel.add(button);
		
		textField_7 = new JTextField();
		textField_7.setBounds(19, 98, 66, 21);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//获取要删除的id
				int id = Integer.parseInt(textField_7.getText());
				//从数据库删除
				itemDao.delete(id);
				//从界面删除
				for(int i = 0;i < data.size();i++) {
					if(data.get(i).get(0).equals(id + "")) {
						data.remove(i);
					}
				}
				//更新UI
				table.updateUI();
				//提示删除成功
				JOptionPane.showMessageDialog(ItemFrame.this, "删除完成", "提示",2);
			}
		});
		button_1.setBounds(354, 97, 93, 23);
		panel.add(button_1);
		
		textField_8 = new JTextField();
		textField_8.setBounds(19, 123, 66, 21);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		JButton button_2 = new JButton("查询");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//获取要查询的商品id
				int id = Integer.parseInt(textField_8.getText());
				Item item = itemDao.selectById(id);
				JOptionPane.showMessageDialog(ItemFrame.this, "商品id为:" + id + "的是" + item.getName() + "，还有" + item.getNum() + "件，每件" + item.getPrice() / 100.0 + "元", "提示",1);
			}
		});
		button_2.setBounds(354, 122, 93, 23);
		panel.add(button_2);
	}
}
