package larson.hm.plugin.dao.impl;

/**
 * 135-0741-8210 ��
 * 138-7596-1035 ��
 * 137-8705-7457 ��
 * 138-0849-9890 ����ɫ
 */
import java.util.List;

import larson.hm.plugin.bean.Homework;
import larson.hm.plugin.util.JdbcUtils;
import larson.hm.plugin.util.MyException;

public class HomeworkDao {

//	public Homework getLastHomework() {
//		String sql = "select * from homework order by id desc limit 1";
//		Homework homework = JdbcUtils.getLastHomework(sql);
//		return homework;
//	}

	/**
	 * 获取给定时间2周内数据
	 * 
	 * @param lastNoticePublishTime
	 * @return 2周内数据
	 */
	public List<Homework> get2WeekHomeworks() {
		// TODO 通过关键字查询
		String sql = "select * from homework order by id";
		return JdbcUtils.get2WeekHomeworks(sql);
	}
	
	/**
	 * 获取给定时间之后的数据
	 * 
	 * @param lastNoticePublishTime
	 * @return 给定时间之后的数据
	 */
	public List<Homework> getAfterHomeworks(String lastHomeworkPublishTime) {
		// TODO 通过关键字查询
		String sql = "select * from homework order by id";
		return JdbcUtils.getAfterHomeworks(sql,lastHomeworkPublishTime);
	}
	
	/**
	 * String subject, String type, String limitTime, String describe, String
	 * voiceUrl, String imageUrl
	 * 
	 * @param homework
	 */
	public void add(Homework homework) {
		try {
			 String sql =
			 "insert into homework(subject,type,content,imageUrl,voiceUrl,limitTime,publishTime) values(?,?,?,?,?,?,?)";
			 Object params[] = {
			 homework.getSubject(),homework.getType(),homework.getDescribe(),homework.getImageUrl(),homework.getVoiceUrl(),homework.getLimitTime(),homework.getPublishTime()};
			 JdbcUtils.update(sql, params);
//			String sql = "insert into homework(subject,type,content,imageUrl,voiceUrl,limitTime) values( '"
//					+ homework.getSubject()
//					+ "','"
//					+ homework.getType()
//					+ "','"
//					+ homework.getDescribe()
//					+ "','"
//					+ homework.getImageUrl()
//					+ "','"
//					+ homework.getVoiceUrl()
//					+ "','" + homework.getLimitTime() + "')";
//			System.out.println(sql);
//			JdbcUtils.addHomework(sql);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}// insert into homework(subject,type,content,imageUrl,voiceUrl,limitTime)
		// values( 'operation system','������','����operation system����ҵ������:�ǻ���ƻ��ڽ���
		// ����10�����룬ͬѧ�ǿ����Ŷ,˳��˵һ�£���vyfuy','null','null','2014-9-5 10:41')

}
