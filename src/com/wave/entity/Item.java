package com.wave.entity;
/*
 * 商品类
 */
public class Item {
	private Integer id;
	private String name;
	private Integer num;
	//价格是浮点数，我们用浮点数乘于100来保存，显示的时候除以100就可以了
	private Integer price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
