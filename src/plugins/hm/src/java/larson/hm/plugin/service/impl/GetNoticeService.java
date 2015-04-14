package larson.hm.plugin.service.impl;

import java.util.List;

import larson.hm.plugin.bean.Notice;
import larson.hm.plugin.dao.impl.NoticeDao;

public class GetNoticeService {

	public static String getNotice(List<Notice> notices) {
		String result = "";

		StringBuilder sb = new StringBuilder();

		// 0。第一布要写消息头
		sb.append("<?xml version='1.0' encoding='utf-8'?>");

		// 服务器没有数据就返回错误
		if (notices == null || notices.size() <= 0) {
			sb.append("<error>");
			sb.append("NODATA");
			sb.append("</error>");
			return sb.toString();
		}
		// 2.服务器有数据就封装数据
		sb.append("<notices>");
		for (Notice notice : notices) {
			sb.append("<notice>");

			String id = notice.getId() + "";
			sb.append("<id>");
			sb.append(id);
			sb.append("</id>");

			String content = notice.getContent();
			sb.append("<content>");
			sb.append(content);
			sb.append("</content>");

			String type = notice.getType() + "";
			sb.append("<type>");
			sb.append(type);
			sb.append("</type>");

			String title = notice.getTitle();
			sb.append("<title>");
			sb.append(title);
			sb.append("</title>");

			String publishTime = notice.getPublishTime() + "";
			sb.append("<publishTime>");
			sb.append(publishTime);
			sb.append("</publishTime>");

			sb.append("</notice>");
		}
		sb.append("</notices>");
		result = sb.toString();
		return result;
	}

	public static String get2WeekNotice() {
		List<Notice> notices = new NoticeDao().get2WeekNotice();
		return getNotice(notices);
	}

	public static String getAfterNotice(String lastNoticePublishTime) {
		List<Notice> notices = new NoticeDao()
				.getAfterNotice(lastNoticePublishTime);
		return getNotice(notices);
	}

}
