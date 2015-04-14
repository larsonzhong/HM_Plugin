package larson.hm.plugin;

import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.xmpp.packet.IQ;

/**
 * 入口的handler，拿到客户端发过来的数据交给其他handler处理，因此，把这个handler放到plugin包下。
 * @author larson
 *
 */
public class WeChatIQHander extends IQHandler {

	private IQHandlerInfo info;

	public WeChatIQHander() {
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
		
		return reply;
	}


}
