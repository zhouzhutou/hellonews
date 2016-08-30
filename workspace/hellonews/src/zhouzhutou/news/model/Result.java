package zhouzhutou.news.model;

public class Result {
    private boolean bool;
    private String message;
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Result(boolean bool, String message) {
		super();
		this.bool = bool;
		this.message = message;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
     
}
