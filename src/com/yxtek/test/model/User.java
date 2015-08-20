/** 
 * File Name:User.java
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

package com.yxtek.test.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String name;
	
	@DatabaseField
	public int age;
}


