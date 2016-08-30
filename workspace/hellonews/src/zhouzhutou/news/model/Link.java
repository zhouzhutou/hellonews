package zhouzhutou.news.model;
/**
 * Link绫�
 * @author zhouzhutou
 *
 */
public class Link {
    private int linkId;
    private String linkName;
    private String linkAddr;
    private String email;
    private int orderNum;
	
	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public Link()
	{
		super();
	}
	public Link(String linkName, String linkAddr, String email, int orderNum) {
		super();
		this.linkName = linkName;
		this.linkAddr = linkAddr;
		this.email = email;
		this.orderNum = orderNum;
	}
	public String getLinkAddr() {
		return linkAddr;
	}
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
    
}
