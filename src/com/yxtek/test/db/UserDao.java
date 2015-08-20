/** 
 * File Name:UserDao.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.yxtek.test.db;

import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.yxtek.test.model.User;

public class UserDao {

	private Context context;
	
	public UserDao(Context context){
		this.context = context;
	}
	
	public void add(final User user) {
		DataBaseHelper.openDb(context);
		
		List<User> users;
		
		try {
			Dao<User, Integer> dao = DataBaseHelper.getInstance().getUserDao();
			users = dao.queryBuilder().where().eq("id", user.id).query();
			if(null!=users&&users.size()>0){
				dao.delete(users);
				dao.create(user);
			}
			else{
				dao.create(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataBaseHelper.closeDb();
	}
	
	public List<User> getList() {
		List<User> list = null;
		
		DataBaseHelper.openDb(context);
		try {
			Dao<User, Integer> dao = DataBaseHelper.getInstance().getUserDao();
			QueryBuilder<User, Integer> builder = dao.queryBuilder();
			list = builder.query();;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataBaseHelper.closeDb();
		return list;
	}
	
	public void delete(User user) {
		DataBaseHelper.openDb(context);
		
		try {
			Dao<User, Integer> dao = DataBaseHelper.getInstance().getUserDao();
			dao.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataBaseHelper.closeDb();
	}
	
	public void delete(List<User> users) {
		DataBaseHelper.openDb(context);
		
		try {
			Dao<User, Integer> dao = DataBaseHelper.getInstance().getUserDao();
			dao.delete(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataBaseHelper.closeDb();
	}
	
	
}


