package me.lv.common.dto;

import me.lv.common.constants.ResponseStatus;

/**
 * @author lv
 */
public class JsonResponse {

	private Integer code;
	private String msg;
	private Object result;

	public JsonResponse() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public static JsonResponse success() {
		return success(null);
	}

	public static JsonResponse success(Object result) {
		return result(ResponseStatus.SUCCESS, null, result);
	}

	public static JsonResponse fail() {
		return fail(null);
	}

	public static JsonResponse fail(String msg) {
		return result(ResponseStatus.FAIL, msg, null);
	}

	private static JsonResponse result(Integer code, String msg, Object result) {
		JsonResponse json = new JsonResponse();
		json.setCode(code);
		json.setMsg(msg);
		json.setResult(result);
		return json;
	}

	@Override
	public String toString() {
		return "JsonResponse{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", result=" + result +
				'}';
	}
}
