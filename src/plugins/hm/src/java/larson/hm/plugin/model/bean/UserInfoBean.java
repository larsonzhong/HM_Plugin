package larson.hm.plugin.model.bean;

/**
 * 所有用户的基类
 * 
 * @author larson
 * @since 2014/8/14 去除了专业。系就表示了专业
 * 
 */
public class UserInfoBean {
	/**
	 * 用户在服务器存储的id号，唯一标示一个用户
	 */
	protected int id;
	/**
	 * 用户名
	 */
	protected String username;
	/**
	 * 密码
	 */
	protected String password;
	/**
	 * 昵称
	 */
	protected String nickname;
	/**
	 * 所在用户组
	 */
	protected String groups;

	/**
	 * 电话号码
	 */
	protected String phone;

	// /**
	// * 所在系别
	// */
	// protected String sdept;
	/**
	 * 所学专业
	 */
	protected String majorName;

	/**
	 * 所在班级
	 */
	protected String classes;
	/**
	 * 学生类别，分为班干部，学委，和普通学生
	 */
	protected String role;

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	// public String getSdept() {
	// return sdept;
	// }
	//
	// public void setSdept(String sdept) {
	// this.sdept = sdept;
	// }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return username + password + nickname + groups + phone + majorName
				+ classes + role;
	}
}
