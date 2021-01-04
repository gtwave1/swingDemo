package com.wave.test;

import com.wave.dao.ItemDao;
import com.wave.entity.Item;

public class Test {
	
	
	
	@org.junit.Test
	public void testDelete() {
		ItemDao itemDao = new ItemDao();
		itemDao.delete(4);
	}
}
