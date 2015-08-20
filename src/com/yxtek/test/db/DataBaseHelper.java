/** 
 * File Name:DataBaseHelper.java 
 * 
 * Version:1.0
 *
 * Date:2013-8-5
 *
 * Description:
 *
 * Author:Erik Wei
 * Email:erik7@126.com
 * Copyright (c) 2013 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */
package com.yxtek.test.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Region.Op;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yxtek.test.model.User;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String SYNC_KEY = "db_sync_key";
	private static final String TAG = "DataBaseHelper";
	private static final String DATABASE_NAME = "qingxue.db";
	private static final int DATABASE_VERSION = 9;
	
	private Dao<User, Integer> mQuaDao;
	
	private static DataBaseHelper instance;
	
	private int 	mDbRetainCount = 0;
	
	private Context context;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource source) {
		createTable();
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase,
			ConnectionSource connectionSource, int arg2, int arg3) {
//		List<DaoQua> list = null;
//		try {
//			try {
//				Dao<DaoQua, Integer> dao = getQuaDao();
//				list = dao.queryForAll();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			TableUtils.dropTable(connectionSource, DaoQua.class, true);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createTable();
//		if (list != null) {
//			for (int nI = 0; nI < list.size(); nI++) {
//				try {
//					Dao<DaoQua, Integer> dao = getQuaDao();
//					dao.create(list.get(nI));
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	private void createTable() {
		try {
			TableUtils.createTable(connectionSource, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public Dao<User, Integer> getUserDao() throws SQLException {
		if (null == mQuaDao) {
			mQuaDao = getDao(User.class);
		}
		return mQuaDao;
	}
	
	/*
	 * 必须先调用openDb
	 * 
	 */
	public static DataBaseHelper getInstance() throws Exception{
		return instance;
	}
	
	public static void openDb(Context context) {
		synchronized (SYNC_KEY) {
			if(instance==null||instance.mDbRetainCount==0){
				instance = OpenHelperManager.getHelper(context, DataBaseHelper.class);
			}
			instance.mDbRetainCount++;
		}
	}

	public static void closeDb() {
		if(null==instance)
			return;
		
		synchronized (SYNC_KEY) {
			if(instance.mDbRetainCount>0){
				instance.mDbRetainCount--;
			}
			else if(instance.mDbRetainCount==0){
					OpenHelperManager.releaseHelper();
			}
		}
		
	}
    
    
}
