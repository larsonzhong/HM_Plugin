package larson.hm.plugin.model.bean;

/**
 * 锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷锟斤拷锟揭筹拷锟绞憋拷锟斤拷adapter锟矫碉拷
 * 
 * @author larson
 *
 */
public class News {

	protected int id;
	/**
	 * 锟斤拷息锟斤拷锟斤拷
	 */
	protected String title;
	/**
	 * 锟斤拷息锟斤拷锟斤拷锟斤拷
	 */
	protected String content;
	/**
	 * 锟斤拷息锟斤拷锟斤拷锟斤拷时锟斤拷
	 */
	protected long publishTime;
	/**
	 * 锟斤拷息锟斤拷锟斤拷锟酵ｏ拷锟斤拷1通知锟斤拷0锟斤拷业锟斤拷锟斤拷锟斤拷2锟筋动
	 */
	protected int type;
	
	public News(int id,String title, String content,long publishTime, int type) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.type = type;
	}
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(String title, String content,long publishTime, int type) {
		super();
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.type = type;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
