package larson.hm.plugin.handler;

import larson.hm.plugin.handler.maker.PacketMaker;

import org.jivesoftware.openfire.MessageRouter;
import org.jivesoftware.openfire.XMPPServer;
import org.xmpp.packet.JID;
import org.xmpp.packet.Message;

public class ToAppHandler {
	private static MessageRouter msgRouter = XMPPServer.getInstance().getMessageRouter(); 

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

}
