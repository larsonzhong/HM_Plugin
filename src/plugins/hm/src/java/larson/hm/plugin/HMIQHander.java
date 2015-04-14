package larson.hm.plugin;

import larson.hm.plugin.handler.FromAppHandler;

import org.dom4j.DocumentException;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.xmpp.packet.IQ;

import pa.wechat.plugin.handler.FromVChatHandler;
import pa.wechat.plugin.util.SysoUtil;

/**
 * 入口的handler，拿到客户端发过来的数据交给其他handler处理，因此，把这个handler放到plugin包下。
 * @author larson
 *
 */
public class HMIQHander extends IQHandler {

	private IQHandlerInfo info;

	public HMIQHander() {
		super(HMPlugin.HM_MODULE_NAME);
		info = new IQHandlerInfo(HMPlugin.HM_MODULE_NAME, HMPlugin.HM_NAMESPACE);
		System.out.println("HMIQHander 初始化。。。");
	}

	@Override
	public IQHandlerInfo getInfo() {
		return info;
	}

	@Override
	public IQ handleIQ(final IQ packet) throws UnauthorizedException {
		final IQ reply = IQ.createResultIQ(packet); 
		new Thread() {
			@Override
			public void run() {
				try {
					FromAppHandler handler = new FromAppHandler();
					handler.handToServerPacket(packet.toXML(),reply.getTo()); 
					SysoUtil.print("--------------"+packet.toXML());
				} catch (DocumentException e) {
					System.out.println("解析失败了");
				}
				
			}
		}.start();
		return reply;
	}


}
