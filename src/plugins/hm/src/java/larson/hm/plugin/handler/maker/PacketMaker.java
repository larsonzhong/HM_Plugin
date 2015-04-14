package larson.hm.plugin.handler.maker;

import larson.hm.plugin.util.SysoUtil;

import org.jivesoftware.openfire.XMPPServer;
import org.xmpp.packet.JID;
import org.xmpp.packet.Message;

public class PacketMaker {

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

}
