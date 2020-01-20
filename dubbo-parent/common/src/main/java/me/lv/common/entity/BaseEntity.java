package me.lv.common.entity;

import java.io.Serializable;

/**
 * @author lzw
 */
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7012047932926183786L;
	
	//系统用户
	public static final String SYSTEM_USER_NAME="system";
	//乐观锁默认值
	public static final Long DEFAULT_VERSION=1L;
	

}
