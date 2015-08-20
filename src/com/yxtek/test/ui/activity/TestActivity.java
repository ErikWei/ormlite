/** 
 * File Name:TestActivity.java
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

package com.yxtek.test.ui.activity;

import java.util.List;

import com.yxtek.ormlite.R;
import com.yxtek.test.db.UserDao;
import com.yxtek.test.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends Activity{

	private static final String TAG = "TestActivity";
	
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		UserDao dao = new UserDao(this);
		
		List<User> users1 = dao.getList();
		dao.delete(users1);
		
		long tm_start = System.currentTimeMillis();
		
		for(int i=0; i<8; i++){
			User user = new User();
//			user.id = 1000+i;
			user.name = "jack"+i;
			user.age = 22+i;
			dao.add(user);
		}
		
		long tm_end = System.currentTimeMillis();
		Log.i(TAG, "spend time:"+(tm_end-tm_start));
		
		List<User> users = dao.getList();
		
		tv = (TextView)findViewById(R.id.textView1);
		
		StringBuilder builder = new StringBuilder();
		
		for(User b:users){
			builder.append("id: "+b.id+"|name: "+b.name+"|age: "+b.age+"\n");
		}
		tv.setText(builder);
	}
	
}


