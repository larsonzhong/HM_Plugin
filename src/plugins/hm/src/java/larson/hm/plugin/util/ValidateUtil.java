package larson.hm.plugin.util;

import larson.hm.plugin.model.holder.FromAppHolder;

/**
 * 一个检验该发起人是否有权执行该指令的工具类
 * 
 * @author larson
 *
 */
public class ValidateUtil {

	public static boolean validateAction(FromAppHolder holder) {
		String cmd = holder.getCmd();
		String role = holder.getUserInfo().getRole();

		if (cmd.equalsIgnoreCase(Constant.Cmd.PUBLISH_NOTICE)) {// 发送通知
			if (role.equalsIgnoreCase(Constant.Role.TEACHER)
					|| role.equalsIgnoreCase(Constant.Role.STUDY_MONITER)
					|| role.equalsIgnoreCase(Constant.Role.LIFE_MONITER)
					|| role.equalsIgnoreCase(Constant.Role.FUN_MONITER)
					|| role.equalsIgnoreCase(Constant.Role.MAIN_MONITER))
				return true;
		} else if (cmd.equalsIgnoreCase(Constant.Cmd.PUBLISH_HOMEWORK)) {// 发布作业
			if (role.equalsIgnoreCase(Constant.Role.TEACHER)
					|| role.equalsIgnoreCase(Constant.Role.STUDY_MONITER))
				return true;
		}
		return false;
	}

}
