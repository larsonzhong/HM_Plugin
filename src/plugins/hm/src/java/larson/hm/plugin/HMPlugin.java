package larson.hm.plugin;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.jivesoftware.admin.AuthCheckFilter;
import org.jivesoftware.openfire.IQRouter;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.event.SessionEventDispatcher;
import org.jivesoftware.openfire.event.SessionEventListener;
import org.jivesoftware.openfire.handler.IQHandler;
import org.jivesoftware.openfire.session.Session;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;

public class HMPlugin implements Plugin, SessionEventListener {

	private IQRouter router;
	public static final String HM_MODULE_NAME = "wechat";
	public static final String HM_NAMESPACE = "paChat";
	
	

	@Override
	public void sessionCreated(Session session) {
		JID userJid = session.getAddress();
		// setResult(userJid);
		System.out.println("userJid is " + userJid);
	}

	@Override
	public void initializePlugin(PluginManager manager, File pluginDirectory) {
		SessionEventDispatcher.addListener(this);
		System.out.println("作业插件初始化...");

		// 从客户端->微信
		IQHandler myHandler = new WeChatIQHander();
		IQRouter iqRouter = XMPPServer.getInstance().getIQRouter();
		iqRouter.addHandler(myHandler);

		// 添加微信url为白名单
		AuthCheckFilter.addExclude("wechat/*");
		AuthCheckFilter.addExclude("wechat/wechat/*");

	}

	public void setResult(JID userJid) {
		/**
		 * 构建iq的扩展包//TODO 没用到,当chat客户端连接上插件的时候触发
		 */
		Document document = DocumentHelper.createDocument();
		Element iqe = document.addElement("iq");
		iqe.addAttribute("type", "result");
		iqe.addAttribute("to", userJid.toFullJID());
		iqe.addAttribute("id", new IQ().getID());

		Namespace namespace = new Namespace("", HM_NAMESPACE);// 初始化的消息我就随便弄一个命名空间了
		Element wechat = iqe.addElement(HM_MODULE_NAME);
		wechat.add(namespace);

		// 最后发送出去！
		IQ iq = new IQ(iqe);
		System.out.println("iq " + iq.toXML());
		router.route(iq);
	}

	@Override
	public void destroyPlugin() {
		// 移除微信白名单
		AuthCheckFilter.addExclude("wechat/*");
		AuthCheckFilter.removeExclude("wechat/wechat/*");
	}

	@Override
	public void sessionDestroyed(Session session) {
	}

	@Override
	public void resourceBound(Session session) {
	}

	@Override
	public void anonymousSessionCreated(Session session) {
	}

	@Override
	public void anonymousSessionDestroyed(Session session) {
	}

}