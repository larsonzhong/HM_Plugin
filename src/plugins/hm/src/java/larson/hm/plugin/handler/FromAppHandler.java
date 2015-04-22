package larson.hm.plugin.handler;

import larson.hm.plugin.handler.parser.FromAppXmlParser;
import larson.hm.plugin.model.bean.Homework;
import larson.hm.plugin.model.bean.Notice;
import larson.hm.plugin.model.bean.UserInfoBean;
import larson.hm.plugin.model.holder.FromAppHolder;
import larson.hm.plugin.util.Constant;
import larson.hm.plugin.util.ValidateUtil;

import org.dom4j.DocumentException;
import org.jivesoftware.openfire.IQRouter;
import org.jivesoftware.openfire.XMPPServer;
import org.xmpp.packet.JID;

import com.google.gson.Gson;

/**
 * 处理从客户端发送到openfire的packet的数据并返回处理结果的类,只是一个处理框架，具体的处理由工具类做
 * <p>
 * 
 * 1.把客户端过来的数据交给工具类处理<br>
 * 2.把工具类加工完成的数据返回给客户端<br>
 * 
 * @author larson
 *
 */
public class FromAppHandler {
	public static IQRouter iqRouter = XMPPServer.getInstance().getIQRouter();
	
	/**
	 * 处理VChat客户端发过来的消息
	 * 
	 * @param xml
	 * @param jid
	 * @throws DocumentException
	 */
	public void handToServerPacket(String xml, final JID jid)
			throws DocumentException {

		// 1.解析客户端数据
		FromAppHolder holder = FromAppXmlParser.parseClientXml(xml);

		// 2.验证该用户是否有权执行该操作，如果有则继续执行，否则返回没有授权的消息
		boolean isContinue = ValidateUtil.validateAction(holder);

		// 3.根据指令做相应的处理,并发送处理结果
		String resultCode;
		if (isContinue) {
			resultCode = actionByCMD(holder);
		} else {
			resultCode = Constant.Code.ERROR_PEMISSION_DELINE;
		}
		ToAppHandler.sendResultCode(resultCode,jid);

	}

	private String actionByCMD(FromAppHolder holder) {
		String resultCode = null;
		String cmd = holder.getCmd();
		if(cmd.equalsIgnoreCase(Constant.Cmd.PUBLISH_NOTICE)){
			//1.解析数据
			Gson gson = new Gson();
			String noticeJson = holder.getConetntJson();
			Notice notice = gson.fromJson(noticeJson, Notice.class);
			UserInfoBean userInfo = holder.getUserInfo();
			//2.发送通知
			ToAppHandler.publishNotice(notice,userInfo);
			resultCode = Constant.Code.SYSMSG_PUBLISH_NOTICE_SUCCEED;
			
		}else if(cmd.equalsIgnoreCase(Constant.Cmd.PUBLISH_HOMEWORK)){
			//1.解析数据
			Gson gson = new Gson();
			String homeworkJson = holder.getConetntJson();
			Homework homework = gson.fromJson(homeworkJson, Homework.class);
			UserInfoBean userInfo = holder.getUserInfo();
			ToAppHandler.publishHomework(homework,userInfo);
			resultCode = Constant.Code.SYSMSG_PUBLISH_HOMEWORK_SUCCEED;
		}
		return resultCode;
	}

}
