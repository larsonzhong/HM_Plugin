package larson.hm.plugin.util;

/**
 * 负责向客户端打印的工具
 * 
 * @author larson
 *
 */
public class SysoUtil {

	/**
	 * 统一打印，便于调试和发布
	 * @param str
	 */
	public static void print(String str) {
		if (Constant.IS_DEBUG)
			System.out.println(str);
	}
}
