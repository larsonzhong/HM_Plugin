package larson.hm.plugin.service.impl;

import java.util.List;

import larson.hm.plugin.bean.Homework;
import larson.hm.plugin.dao.impl.HomeworkDao;

public class GetHomeworkService {

	/**
	 * String subject, String type, String limitTime, String describe, String
	 * voiceUrl, String imageUrl
	 * 
	 * @return
	 */
	public static String getHomeworks(List<Homework> homeworks) {
		String result = "";
		StringBuilder sb = new StringBuilder();

		// 1.第一布要写消息头
		sb.append("<?xml version='1.0' encoding='utf-8'?>");

		// 2.服务器没有数据就返回错误
		if (homeworks == null || homeworks.size() <= 0) {
			sb.append("<error>");
			sb.append("NODATA");
			sb.append("</error>");
			return sb.toString();
		}
		
		//3.如果有数据就写给客户端
		sb.append("<homeworks>");
		for (Homework homework : homeworks) {
			sb.append("<homework>");

			sb.append("<id>");
			String id = homework.getId() + "";
			sb.append(id);
			sb.append("</id>");

			sb.append("<subject>");
			String subject = homework.getSubject();
			sb.append(subject);
			sb.append("</subject>");

			sb.append("<type>");
			String type = homework.getType();
			sb.append(type);
			sb.append("</type>");

			sb.append("<limitTime>");
			String limitTime = homework.getLimitTime();
			sb.append(limitTime);
			sb.append("</limitTime>");

			sb.append("<publishTime>");
			String publishTime = homework.getPublishTime()+"";
			sb.append(publishTime);
			sb.append("</publishTime>");

			sb.append("<describe>");
			String describe = homework.getDescribe();
			sb.append(describe);
			sb.append("</describe>");

			sb.append("<voiceUrl>");
			String voiceUrl = homework.getVoiceUrl();
			if (voiceUrl != null && !"".equalsIgnoreCase(voiceUrl))
				sb.append(voiceUrl);
			sb.append("</voiceUrl>");

			sb.append("<imageUrl>");
			String imageUrl = homework.getImageUrl();
			if (imageUrl != null && !"".equalsIgnoreCase(imageUrl))
				sb.append(imageUrl);
			sb.append("</imageUrl>");

			sb.append("</homework>");
		}
		sb.append("</homeworks>");
		result = sb.toString();
		return result;
	}

	public static String get2WeekHomework() {
		List<Homework> homeworks = new HomeworkDao().get2WeekHomeworks();
		return getHomeworks(homeworks);
	}

	public static String getAfterHomework(String lastHomeworkPublishTime) {
		List<Homework> homeworks = new HomeworkDao()
				.getAfterHomeworks(lastHomeworkPublishTime);
		return getHomeworks(homeworks);
	}

}
