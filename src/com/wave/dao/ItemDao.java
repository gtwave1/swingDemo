package com.wave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.wave.entity.Item;

public class ItemDao {
	
	public int add(Item item) {
		String sql = "insert into item(name,num,price) values(?,?,?) ";
		Connection conn = DBUtil.getConnection();
		int id = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getNum());
			pstmt.setInt(3, item.getPrice());
			pstmt.executeUpdate();
			ResultSet res = pstmt.getGeneratedKeys();
			while(res.next()) {
				id = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void delete(Integer id) {
		String sql = "delete from item where id = ?";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(Integer id,Item item) {
		String sql = "update item set name = ?,num = ?,price = ? where id = ?";
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getNum());
			pstmt.setInt(3, item.getPrice());
			pstmt.setInt(4, id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Vector<Vector<String> > selectAll(){
		//因为JTable要求传入的参数就是一个Vector,所以这里就直接用Vector了
		Vector<Vector<String> > vectors = new Vector<>();
		String sql = "select * from item";
		Connection conn = DBUtil.getConnection();
		ResultSet res = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				//Item t = new Item();
				//因为用来显示的Table只能放Vector，所以这里直接用一个Vector就好了
				Vector<String> t = new Vector<>();
				t.add(res.getInt("id") + "");
				t.add(res.getString("name"));
				t.add(res.getInt("num") + "");
				t.add((res.getInt("price") / 100.0) + "");
				vectors.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vectors;
	}
	
	public Item selectById(Integer id) {
		String sql = "select * from item where id = ?";
		Connection conn = DBUtil.getConnection();
		Item item = new Item();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				item.setId(res.getInt("id"));
				item.setName(res.getString("name"));
				item.setNum(res.getInt("num"));
				item.setPrice(res.getInt("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
}
