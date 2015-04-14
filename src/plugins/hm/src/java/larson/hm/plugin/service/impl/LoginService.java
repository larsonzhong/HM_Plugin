package larson.hm.plugin.service.impl;

import larson.hm.plugin.bean.User;
import larson.hm.plugin.dao.impl.UserDao;
import larson.hm.plugin.util.Constant;

public class LoginService {
	/**
	 * ��֤�û�������� 410-------�û�����Ϊ�� 411-------�û�������벻��ȷ
	 * 
	 * @param username
	 * @param password
	 * @param major
	 * @return
	 */
	public static String Validate(String username, String password, String major) {
		String resultCode = "";
		// 1.�ǿ�У��
		if (username == null || "".equals(username))
			resultCode = Constant.IS_EMPYT;
		// 2.�û�����У��
		UserDao dao = new UserDao();
		User user = dao.validate(username, password, major);
		// 3.������ȷ������ȷ����������û���������벻��ȷ
		if (user != null) {
			String result = makeBean(user);
			return result;
		} else
			resultCode = Constant.USER_NOT_EXIST;

		StringBuilder sb = new StringBuilder();

		// 0。第一布要写消息头
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");

		sb.append("<resultCode>");
		sb.append(resultCode);
		sb.append("</resultCode>");
		return sb.toString();
	}

	/**
	 * ��user���������xml�ĵ� uid,username,password,nickname,group,phone
	 * 
	 * @param user
	 * @return
	 */
	private static String makeBean(User user) {
		StringBuilder sb = new StringBuilder();

		// 1.��������
		sb.append("<users>");
		sb.append("<user>");

		// 2.״̬��
		sb.append("<resultCode>");
		sb.append(Constant.OK);
		sb.append("</resultCode>");

		sb.append("<id>");
		sb.append(user.getId());
		sb.append("</id>");

		sb.append("<username>");
		sb.append(user.getUsername());
		sb.append("</username>");

		sb.append("<password>");
		sb.append(user.getPassword());
		sb.append("</password>");

		sb.append("<nickname>");
		sb.append(user.getNickname());
		sb.append("</nickname>");

		sb.append("<groups>");
		sb.append(user.getGroups());
		sb.append("</groups>");

		sb.append("<phone>");
		sb.append(user.getPhone());
		sb.append("</phone>");

		sb.append("<majorName>");
		sb.append(user.getMajorName());
		sb.append("</majorName>");

		sb.append("<classes>");
		sb.append(user.getClasses());
		sb.append("</classes>");

		sb.append("<type>");
		sb.append(user.getType());
		sb.append("</type>");

		sb.append("</user>");
		sb.append("</users>");

		return sb.toString();
	}
}
