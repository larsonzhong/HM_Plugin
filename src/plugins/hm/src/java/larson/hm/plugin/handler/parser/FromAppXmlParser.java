package larson.hm.plugin.handler.parser;

import larson.hm.plugin.HMPlugin;
import larson.hm.plugin.model.bean.FromAppContentBean;
import larson.hm.plugin.model.bean.UserInfoBean;
import larson.hm.plugin.model.holder.FromAppHolder;
import larson.hm.plugin.util.SysoUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;

public class FromAppXmlParser {

	/**
	 * 解析来自客户端的packet数据
	 * 
	 * @param xml
	 * @return FromAppHolder 返回VChat客户端发过来的指令和数据的FromAppHolder实例
	 * @throws DocumentException
	 */
	public static FromAppHolder parseClientXml(String xml)
			throws DocumentException {
		//1.解析xml文件
		Document doc = DocumentHelper.parseText(xml);
		Element iqe = doc.getRootElement();
		Element pace = iqe.element(HMPlugin.HM_MODULE_NAME);
		String cmd = pace.element("command").getText();
		String contentJson = pace.element("contentJson").getText();
		String userInfoJson = pace.element("userInfoJson").getText();
		//2.转换实例对象
		Gson gson = new Gson();
		FromAppContentBean conetnt = gson.fromJson(contentJson, FromAppContentBean.class);
		UserInfoBean userInfo =  gson.fromJson(userInfoJson, UserInfoBean.class);
		//3.填充实例数据
		FromAppHolder holder = new FromAppHolder();
		holder.setCmd(cmd);
		holder.setConetnt(conetnt);
		holder.setUserInfo(userInfo);
		
		SysoUtil.print("the cmd is "+cmd);
 		return holder;
	}


}
