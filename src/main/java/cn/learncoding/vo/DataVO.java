package cn.learncoding.vo;

public class DataVO<T> {
	
	private static final DataVO<Object> SUCCESS_DEFAULT_DATA = new DataVO<>(true, "", null);
	
	private T data;
	private boolean flag;
	private String msg;
	
	
	public static DataVO<Object> errorData(String msg){
        return new DataVO<Object>(false, msg, null);
    }
	
    public static DataVO<Object> successDefaultData(){
        return SUCCESS_DEFAULT_DATA;
    }

    public static DataVO<Object> successData(Object data){
        return new DataVO<>(true, "", data);
    }
	
	public DataVO() {
		flag = true;
	}
	
	public DataVO(boolean flag, String msg, T data) {
		super();
		this.data = data;
		this.flag = flag;
		this.msg = msg;
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
