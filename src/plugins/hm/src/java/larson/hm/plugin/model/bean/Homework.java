package larson.hm.plugin.model.bean;

public class Homework {
	/**
	 * 作业号，唯一标示一个作业
	 */
	private int id;
	/**
	 * 作业的科目
	 */
	private String subject;
	/**
	 * 作业的类型（必须做，选做）
	 */
	private String type;
	/**
	 * 作业上交时间
	 */
	private String limitTime;
	/**
	 * 发布时间（用来匹配消息接收）
	 */
	private long publishTime;

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * 作业描述
	 */
	private String describe;
	/**
	 * 作业语音
	 */
	private String voiceUrl;
	/**
	 * 作业图片
	 */
	private String imageUrl;
	/**
	 * 是否需要学生给予反馈
	 */
	private int needBack;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getNeedBack() {
		return needBack;
	}

	public void setNeedBack(int needBack) {
		this.needBack = needBack;
	}

	public Homework(String subject, String type, String limitTime,long publishTime,
			String describe, String voiceUrl, String imageUrl) {
		super();
		this.subject = subject;
		this.type = type;
		this.limitTime = limitTime;
		this.describe = describe;
		this.voiceUrl = voiceUrl;
		this.imageUrl = imageUrl;
		this.publishTime = publishTime;
	}

	public Homework() {
	}

	@Override
	public String toString() {
		return "subject = "+subject+"type = "+type+"limitTime = "+limitTime+"describe="+describe+"publishTime="+publishTime;
	}
	
}
