package zhouzhutou.news.model;

public class PageBean {
    private int page;
    private int size;
    private int start;
    
	public PageBean(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
    public void setStart()
    {
    	start=(page-1)*size;
    }
    public int getStart()
    {
    	return start;
    }
}
