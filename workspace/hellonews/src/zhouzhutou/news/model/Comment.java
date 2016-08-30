package zhouzhutou.news.model;

import java.util.Date;
/**
 * Comment
 * @author zhouzhutou
 *
 */
public class Comment {
    public Comment(int newsId) {
		super();
		this.newsId = newsId;
	}
	private int commentId;
    private int newsId=-1;
	private String comment;
    private Date createAt;
    private String title;
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String userName;
    
    public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
