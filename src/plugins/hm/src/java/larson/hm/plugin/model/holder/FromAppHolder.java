package larson.hm.plugin.model.holder;

import larson.hm.plugin.model.bean.UserInfoBean;

public class FromAppHolder {
	private String cmd;
	private String conetntJson;
	private UserInfoBean userInfo;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getConetntJson() {
		return conetntJson;
	}

	public void setConetntJson(String conetntJson) {
		this.conetntJson = conetntJson;
	}

	public UserInfoBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}

}
