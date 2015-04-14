package larson.hm.plugin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import larson.hm.plugin.handler.BeanHandler;
import larson.hm.plugin.handler.ResultSetHandler;
import larson.hm.plugin.model.bean.Homework;
import larson.hm.plugin.model.bean.Major;
import larson.hm.plugin.model.bean.Notice;
import larson.hm.plugin.model.bean.Subject;
import larson.hm.plugin.model.bean.User;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ��ݿ����
 * 
 * @author Larson
 * 
 */
public class JdbcUtils {

	private static ComboPooledDataSource ds = null;
	// private static Connection conn;

	static {
		try {
			/**
			 * ��һ���´������ļ�����ȥ�� InputStream in =
			 * JdbcUtils.class.getClassLoader
			 * ().getResourceAsStream("db.properties"); Properties properties =
			 * new Properties(); properties.load(in); String url =
			 * properties.getProperty("url"); String user =
			 * properties.getProperty("user"); String passwd =
			 * properties.getProperty("password"); String driver =
			 * properties.getProperty("driver"); Class.forName(driver); conn =
			 * DriverManager.getConnection(url, user, passwd)
			 * �ڶ�����ֱ���ڴ�������д ds.setDriverClass(driver);
			 * ds.setJdbcUrl(url); ds.setUser(user); ds.setPassword(passwd);
			 * 
			 * ds.setInitialPoolSize(10); ds.setMinPoolSize(5);
			 * ds.setMaxPoolSize(20);
			 * ��������ͨ��XML�ļ������XML�ļ��������classPath
			 * ·�����棬Ȼ�����ָ�����ص�configName,��ָ��ʹ��Ĭ�ϵ�
			 * java�������Զ����������ļ������������������ܾõ�
			 */
			ds = new ComboPooledDataSource("mysql");

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * �ͷ�����
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		//连接数过多的时候我手动关闭
		try {
			if(ds.getNumConnections()>200)
				ds.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;

		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �滻dao�е���ɾ�ķ���
	 * 
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public static void update(String sql, Object params[]) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			/**
			 * ͨ�����dmd���Ի�ȡ����ݿ������й���Ϣ DatabaseMetaData dmd =
			 * conn.getMetaData(); dmd.getDatabaseProductName();
			 */
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			System.out.println(ps.toString());
			ps.executeUpdate();

		} finally {
			release(conn, ps, rs);
		}
	}

	/**
	 * �滻����dao�еĲ�ѯ ����ģʽ(��ʱ����ԭʼ�ķ���validateQuery()���)
	 * 
	 * @param sql
	 * @param params
	 * @param rsh
	 * @return
	 * @throws SQLException
	 */
	public static Object query(String sql, Object params[], ResultSetHandler rsh)
			throws SQLException {

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
	 * ��½У��
	 */
	public static User validateQuery(String sql) throws SQLException {
		User user = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			System.out.println("登陆验证---"+sql);
			rs = st.executeQuery();
			user = (User) new BeanHandler(User.class).handler(rs);
			return user;
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
		Statement st = null;
		ResultSet rs = null;

		Major major = null;
		List<Major> majors = new ArrayList<Major>();

		try {
			conn = getConnection();
			st = conn.createStatement();
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
	 * ��ȡ���е�subject
	 * 
	 * @param beanHandler
	 * @param sql
	 * @return
	 */
	public static List<Subject> getAllSubjects(String sql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		Subject subject = null;
		List<Subject> subjects = new ArrayList<Subject>();

		try {
			conn = getConnection();
			st = conn.createStatement();
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

//	/**
//	 * ��ȡ���е�subject
//	 * 
//	 * @param beanHandler
//	 * @param sql
//	 * @return
//	 */
//	public static Homework getLastHomework(String sql) {
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//
//		Homework homework = null;
//
//		try {
//			conn = getConnection();
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//
//			if (rs.next()) {
//				homework = new Homework();
//				homework.setDescribe(rs.getString("content"));
//				homework.setId(rs.getInt("id"));
//				homework.setImageUrl(rs.getString("imageUrl"));
//				homework.setLimitTime(rs.getString("limitTime"));
//				homework.setSubject(rs.getString("subject"));
//				homework.setType(rs.getString("type"));
//				homework.setPublishTime(rs.getString("publishTime"));
//				homework.setVoiceUrl(rs.getString("voiceUrl"));
//			}
//			return homework;
//		} catch (Exception e) {
//			throw new MyException(e);
//		} finally {
//			release(conn, st, rs);
//		}
//	}

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
		Statement st = null;
		ResultSet rs = null;

		Notice notice = null;
		List<Notice> notices = new ArrayList<Notice>();

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

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
			release(conn, st, rs);
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
	 * @param sql
	 * @param lastNoticePublishTime
	 * @return
	 */
	public static List<Notice> getAfterNotice(String sql,
			String lastNoticePublishTime) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		Notice notice = null;
		List<Notice> notices = new ArrayList<Notice>();

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setType(rs.getInt("type"));

				long sqlPubT = Long.parseLong(rs.getString("publishTime"));
				notice.setPublishTime(sqlPubT);
				if (isAfterTime(sqlPubT,lastNoticePublishTime))
					notices.add(notice);
				notice = null;
			}
			return notices;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, st, rs);
		}
	}

	/**
	 * 给定的时间lastNoticePublishTime是不是在查询的数据之前，是的话true
	 * @param pubT 查询到的数据发布时间
	 * @param lastNoticePublishTime 软件收到最后的消息时间
	 * @return 
	 */
	private static boolean isAfterTime(long sqlPubT, String lastNoticePublishTime) {
		long lastTime = Long.parseLong(lastNoticePublishTime);
		if ((sqlPubT - lastTime)>0)
			return true;
		else
			return false;
	}
	
	public static List<Homework> getAfterHomeworks(String sql,
			String lastHomeworkPublishTime) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		Homework homework = null;
		List<Homework> homeworks = new ArrayList<Homework>();

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

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
				if (isAfterTime(pubT,lastHomeworkPublishTime))
					homeworks.add(homework);
				homework = null;
			}
			return homeworks;
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			release(conn, st, rs);
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
		Statement st = null;
		ResultSet rs = null;

		Homework homework = null;
		List<Homework> homeworks = new ArrayList<Homework>();

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

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
			release(conn, st, rs);
		}
	}

}
