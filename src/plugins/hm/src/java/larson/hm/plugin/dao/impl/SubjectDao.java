package larson.hm.plugin.dao.impl;

import java.util.List;

import larson.hm.plugin.model.bean.Subject;
import larson.hm.plugin.util.JdbcUtils;
import larson.hm.plugin.util.MyException;

public class SubjectDao {

	public void add(Subject subject) {
		try {
			String sql = "insert into subject(name,majorName) values(?,?)";
			Object params[] = { subject.getName(),subject.getMajorName() };
			JdbcUtils.update(sql, params);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

	public List<Subject> getAll(){
		String sql = "select id,name,majorName from subject";
		List<Subject> subjects = JdbcUtils.getAllSubjects(sql);
		return subjects;
	}
}
