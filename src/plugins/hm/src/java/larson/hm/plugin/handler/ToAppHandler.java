package larson.hm.plugin.handler;

import java.util.List;

import larson.hm.plugin.dao.impl.UserDao;
import larson.hm.plugin.handler.maker.PacketMaker;
import larson.hm.plugin.model.bean.Homework;
import larson.hm.plugin.model.bean.Notice;
import larson.hm.plugin.model.bean.UserInfoBean;

import org.jivesoftware.openfire.MessageRouter;
import org.jivesoftware.openfire.XMPPServer;
import org.xmpp.packet.JID;
import org.xmpp.packet.Message;

import com.google.gson.Gson;

public class ToAppHandler {
	private static MessageRouter msgRouter = XMPPServer.getInstance()
			.getMessageRouter();

	/**
	 * 服务器处理数据后的返回码
	 * 
	 * @param resultCode
	 * @param iqRouter
	 * @param jid
	 */
	public static void sendResultCode(String resultCode, JID jID) {
		Message packet = PacketMaker.makeToAppResultCode(jID, resultCode);
		msgRouter.route(packet);
	}

	/**
	 * 发布通知，通过判断发送消息的人所在的班级号，遍历数据库中对应的jid，循环发送
	 * 
	 * @param notice
	 * @param userInfo
	 */
	public static void publishNotice(Notice notice, UserInfoBean userInfo) {
		// 1.查询数据库里面classNo为userInfo.getClassNo()的人。
		UserDao dao = new UserDao();
		List<JID> jids = dao.queryForClassNo(userInfo.getClassNo());

		// 2.序列化通知数据
		String noticeContent = new Gson().toJson(notice);

		// 3.循环遍历集合，发送消息
		for (JID jid : jids) {
			Message packet = PacketMaker.publishNotice(jid, noticeContent);
			msgRouter.route(packet);
		}
	}

	/**
	 * 把作业信息发出去
	 * 
	 * @param homework
	 * @param userInfo
	 */
	public static void publishHomework(Homework homework, UserInfoBean userInfo) {
		// TODO Auto-generated method stub

	}

}
