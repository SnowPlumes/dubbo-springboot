package com.meitu.common.dto;

import java.io.Serializable;

/**
 * 
 * @author u
 * 
 */
public class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3631883424406056443L;

	private int code = 0;// 返回代码1：成功0：失败
	private String msg;// 返回信息描述
	private Object results;
	
	public BaseResponse(){
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

}
