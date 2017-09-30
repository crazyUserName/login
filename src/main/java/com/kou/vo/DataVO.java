package com.kou.vo;

public class DataVO<T> {
	
	public static enum Flag{
		SUCCESS(true),ERROR(false);
		
		private boolean value;
		
		private Flag(boolean value) {
			this.value = value;
		}

		public boolean getValue() {
			return value;
		}

	}
	
	private T data;
	private boolean flag;
	private String msg;
	
	public DataVO() {
		flag = Flag.SUCCESS.value;
	}
	
	public DataVO(T data) {
		super();
		this.data = data;
		flag = Flag.SUCCESS.value;
	}
	
	public DataVO(String msg) {
		super();
		this.msg = msg;
		flag = Flag.ERROR.value;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlagEnum(Flag flag) {
		this.flag = flag.value;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
