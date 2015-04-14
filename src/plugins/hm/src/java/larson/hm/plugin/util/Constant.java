package larson.hm.plugin.util;

public class Constant {

	/**
	 * 是否开启调试
	 */
	public static final boolean IS_DEBUG = true;

	public static final String USER_NOT_EXIST = "601";
	public static final String OK = "602";
	/**
	 * ��ʾ��⵽�������ļ�
	 */
	public static final String RESULT_NEED_VOICE = "RESULT_NEED_VOICE";
	/**
	 * ��Ҫ�ͻ��˷�¼������
	 */
	public static final String READ_VOICE_FAILED = "READ_VOICE_FAILED";
	/**
	 * ��Ҫ�ͻ��˷���ͼ����
	 */
	public static final String RESULT_NEED_IMAGE = "RESULT_NEED_IMAGE";
	/**
	 * ��ҵ�����ɹ�
	 */
	public static final String PUBLISH_COMPLETE = "PUBLISH_COMPLETE";

	/**
	 * ��ҵ�����ɹ�
	 */
	public static final String PUBLISH_FAILED = "PUBLISH_FAILED";

	public static final int TYPE_HOMEWORK = 0;
	public static final int TYPE_NOTICE = 1;

	/**
	 * 用户角色
	 * @author larson
	 *
	 */
	public static final class Role{

		public static final String TEACHER = "teacher";
		public static final String STUDY_MONITER = "study_monitor";
		public static final String MAIN_MONITER = "main_monitor";
		public static final String LIFE_MONITER = "life_monitor";
		public static final String FUN_MONITER = "fun_monitor";
	}

	/**
	 * 客户端请求服务器的指令
	 * @author larson
	 *
	 */
	public static final class Cmd {

		public static final String PUBLISH_NOTICE = "publish_notice";//发布通知
		public static final String PUBLISH_HOMEWORK = "publish_homework";//发布作业
		
	}

	/**
	 * 客户端情妇服务端的消息返回码
	 */
	public static final class Code {

		public static final String ERROR_PEMISSION_DELINE = "pession_deline";// 该用户没有权限操作这条指令

	}

	public static String IS_EMPYT = "600";
}
