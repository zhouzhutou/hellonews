package zhouzhutou.news.model;

import java.util.Date;
/**
 * News绫�
 * @author zhouzhutou
 *
 */
public class News {
    private int newsId;
    private String title;
    private String newsContent;
    private String author;
    private int isHead;
    private int isHot;
    private int isImage;
    private String imageName;
    private int newsTypeId=-1;
    private String newsTypeName;
    private Date createAt;
    private int click;
    
    public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(int newsId,String title)
    {
    	super();
    	this.newsId=newsId;
    	this.title=title;
    }
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public void setAuthor(String author)
	{
		this.author=author;
	}
	public String getAuthor()
	{
		return author;
	}
	public int getIsHead() {
		return isHead;
	}
	public void setIsHead(int isHead) {
		this.isHead = isHead;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	public int getIsImage() {
		return isImage;
	}
	public void setIsImage(int isImage) {
		this.isImage = isImage;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
    
}
