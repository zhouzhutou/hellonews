package zhouzhutou.news.model;
/**
 * NewsType绫�
 * @author zhouzhutou
 *
 */
public class NewsType {
    private int newsTypeId;
    public String newsTypeName;
    public NewsType(String newsTypeName) {
		// TODO Auto-generated constructor stub
    	super();
    	this.newsTypeName=newsTypeName;
	}
    public NewsType() {
		// TODO Auto-generated constructor stub
    	super();
	}
	public int getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public String getNewsTypeName() {
		return newsTypeName;
	}
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}

	
}
