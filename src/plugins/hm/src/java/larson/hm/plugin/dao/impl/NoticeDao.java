package larson.hm.plugin.dao.impl;

import java.util.List;

import larson.hm.plugin.bean.Notice;
import larson.hm.plugin.util.JdbcUtils;

public class NoticeDao {

	// id, title, content, type
	public boolean add(Notice notice) {
		try {
			String sql = "insert into notice(title,content,publishTime,type) values(?,?,?,?)";
			Object params[] = { notice.getTitle(), notice.getContent(),
					notice.getPublishTime(), notice.getType() };
			JdbcUtils.update(sql, params);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取给定时间2周内数据
	 * 
	 * @param lastNoticePublishTime
	 * @return 2周内数据
	 */
	public List<Notice> get2WeekNotice() {
		// TODO 通过关键字查询
		String sql = "select * from notice order by id";
		return JdbcUtils.get2WeekNotice(sql);
	}
	/**
	 * 获取给定时间之后的数据
	 * 
	 * @param lastNoticePublishTime
	 * @return 给定时间之后的数据
	 */
	public List<Notice> getAfterNotice(String lastNoticePublishTime) {
		// TODO 通过关键字查询
		String sql = "select * from notice order by id";
		return JdbcUtils.getAfterNotice(sql,lastNoticePublishTime);
	}

}
