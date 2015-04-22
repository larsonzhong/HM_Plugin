package larson.hm.plugin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import larson.hm.plugin.handler.ResultSetHandler;
import larson.hm.plugin.model.bean.Homework;
import larson.hm.plugin.model.bean.Major;
import larson.hm.plugin.model.bean.Notice;
import larson.hm.plugin.model.bean.Subject;

import org.jivesoftware.database.DbConnectionManager;

/**
 * ��ݿ����
 * 
 * @author Larson
 * 
 */
public class JdbcUtils {

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DbConnectionManager.getConnection();
	}

	/**
	 * 关闭连接
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void release(Connection connection,
			PreparedStatement statement, ResultSet resultSet) {
		DbConnectionManager.closeConnection(resultSet, statement, connection);
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public static int update(String sql, Object params[]) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int effectRaw = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			effectRaw = ps.executeUpdate();

		} finally {
			release(conn, ps, rs);
		}
		return effectRaw;
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @param rsh
	 * @return
	 * @throws Exception
	 */
	public static <T> Object query(String sql, Object params[],
			ResultSetHandler rsh) throws Exception {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
			}
			rs = st.executeQuery();
			return rsh.handler(rs);

		} finally {
			release(conn, st, rs);
		}
	}

	/**
	 * ��ȡ���е�major
	 * 
	 * @param beanHandler
	 * @param sql
	 * @return
	 */
	public static List<Major> getAllMajors(String sql) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Major major = null;
		List<Major> majors = new ArrayList<Major>();

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				major = new Major();
				major.setId(rs.getInt("id"));
				major.setName(rs.getString("name"));
				majors.add(major);
				major = null;
			}
			return majors;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, st, rs);
		}
	}

	/**
	 * 
	 * @param beanHandler
	 * @param sql
	 * @return
	 */
	public static List<Subject> getAllSubjects(String sql) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Subject subject = null;
		List<Subject> subjects = new ArrayList<Subject>();

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				subject.setMajorName(rs.getString("majorName"));
				subjects.add(subject);
				subject = null;
			}
			return subjects;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, st, rs);
		}
	}

	/**
	 * ��ʱ�õ�homework���
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public static void addHomework(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} finally {
			release(conn, ps, rs);
		}
	}

	/**
	 * 获取给定时间的前两周的数据
	 * 
	 * @param sql
	 * @return
	 */
	public static List<Notice> get2WeekNotice(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Notice notice = null;
		List<Notice> notices = new ArrayList<Notice>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setType(rs.getInt("type"));

				long pubT = Long.parseLong(rs.getString("publishTime"));
				notice.setPublishTime(pubT);
				if (is2Week(pubT))
					notices.add(notice);
				notice = null;
			}
			return notices;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, ps, rs);
		}
	}

	private static boolean is2Week(long pubT) {
		long curT = System.currentTimeMillis();
		if ((curT - pubT) / (1000 * 3600 * 24 * 14) < 1)
			return true;
		else
			return false;
	}

	/**
	 * 获取客户端发布时间以后的消息
	 * 
	 * @param sql
	 * @param lastNoticePublishTime
	 * @return
	 */
	public static List<Notice> getAfterNotice(String sql,
			String lastNoticePublishTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Notice notice = null;
		List<Notice> notices = new ArrayList<Notice>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setType(rs.getInt("type"));

				long sqlPubT = Long.parseLong(rs.getString("publishTime"));
				notice.setPublishTime(sqlPubT);
				if (isAfterTime(sqlPubT, lastNoticePublishTime))
					notices.add(notice);
				notice = null;
			}
			return notices;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, ps, rs);
		}
	}

	/**
	 * 给定的时间lastNoticePublishTime是不是在查询的数据之前，是的话true
	 * 
	 * @param pubT
	 *            查询到的数据发布时间
	 * @param lastNoticePublishTime
	 *            软件收到最后的消息时间
	 * @return
	 */
	private static boolean isAfterTime(long sqlPubT,
			String lastNoticePublishTime) {
		long lastTime = Long.parseLong(lastNoticePublishTime);
		if ((sqlPubT - lastTime) > 0)
			return true;
		else
			return false;
	}

	public static List<Homework> getAfterHomeworks(String sql,
			String lastHomeworkPublishTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Homework homework = null;
		List<Homework> homeworks = new ArrayList<Homework>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				homework = new Homework();
				homework.setId(rs.getInt("id"));
				homework.setId(rs.getInt("id"));
				homework.setDescribe(rs.getString("content"));
				homework.setImageUrl(rs.getString("imageUrl"));
				homework.setLimitTime(rs.getString("limitTime"));
				homework.setSubject(rs.getString("subject"));
				homework.setType(rs.getString("type"));
				homework.setVoiceUrl(rs.getString("voiceUrl"));

				long pubT = Long.parseLong(rs.getString("publishTime"));
				homework.setPublishTime(pubT);
				if (isAfterTime(pubT, lastHomeworkPublishTime))
					homeworks.add(homework);
				homework = null;
			}
			return homeworks;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, ps, rs);
		}
	}

	/**
	 * 获取给定时间的前两周的数据
	 * 
	 * @param sql
	 * @return
	 */
	public static List<Homework> get2WeekHomeworks(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Homework homework = null;
		List<Homework> homeworks = new ArrayList<Homework>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				homework = new Homework();
				homework.setId(rs.getInt("id"));
				homework.setDescribe(rs.getString("content"));
				homework.setImageUrl(rs.getString("imageUrl"));
				homework.setLimitTime(rs.getString("limitTime"));
				homework.setSubject(rs.getString("subject"));
				homework.setType(rs.getString("type"));
				homework.setVoiceUrl(rs.getString("voiceUrl"));

				long pubT = Long.parseLong(rs.getString("publishTime"));
				homework.setPublishTime(pubT);
				if (is2Week(pubT))
					homeworks.add(homework);
				homework = null;
			}
			return homeworks;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, ps, rs);
		}
	}

}
