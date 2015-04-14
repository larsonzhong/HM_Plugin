package larson.hm.plugin.model.holder;

import larson.hm.plugin.model.bean.FromAppContentBean;
import larson.hm.plugin.model.bean.UserInfoBean;

public class FromAppHolder {
	private String cmd;
	private FromAppContentBean conetnt;
	private UserInfoBean userInfo;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public FromAppContentBean getConetnt() {
		return conetnt;
	}

	public void setConetnt(FromAppContentBean conetnt) {
		this.conetnt = conetnt;
	}

	public UserInfoBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}

}
