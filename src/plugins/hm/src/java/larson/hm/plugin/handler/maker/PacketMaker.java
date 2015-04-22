package larson.hm.plugin.handler.maker;

import larson.hm.plugin.util.SysoUtil;

import org.jivesoftware.openfire.XMPPServer;
import org.xmpp.packet.JID;
import org.xmpp.packet.Message;

public class PacketMaker {

	/**
	 * 返回指令处理结果给执行者
	 * @param jID
	 * @param resultCode
	 * @return
	 */
	public static Message makeToAppResultCode(JID jID, String resultCode) {
		Message ofMsg = new Message();

		String from = "wxServer@"+XMPPServer.getInstance().getServerInfo().getHostname()+"/Smack";
		ofMsg.setFrom(from); 
		ofMsg.setTo(jID);
		ofMsg.setType(Message.Type.chat); 
		ofMsg.setSubject("resultCode"); 

		SysoUtil.print("发送resultCode");
		return ofMsg;
	}

	/**
	 * 发布通知
	 * @param jid
	 * @param noticeContent
	 * @return
	 */
	public static Message publishNotice(JID jid, String noticeContent) {
		Message ofMsg = new Message();

		String from = "wxServer@"+XMPPServer.getInstance().getServerInfo().getHostname()+"/Smack";
		ofMsg.setFrom(from); 
		ofMsg.setTo(jid);
		ofMsg.setType(Message.Type.headline); 
		ofMsg.setSubject("notice"); 

		SysoUtil.print("发送notice");
		return ofMsg;
	}

}
