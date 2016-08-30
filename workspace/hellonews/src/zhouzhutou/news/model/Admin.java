package zhouzhutou.news.model;

public class Admin {
    private int adminId;
    private String name;
    private String password;
    
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public Admin(int adminId, String name, String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.password = password;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
