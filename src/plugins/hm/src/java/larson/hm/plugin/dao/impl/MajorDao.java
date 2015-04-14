package larson.hm.plugin.dao.impl;

import java.util.List;

import larson.hm.plugin.bean.Major;
import larson.hm.plugin.util.JdbcUtils;
import larson.hm.plugin.util.MyException;

public class MajorDao {

	public void add(Major major) {
		try {
			String sql = "insert into major(name) values(?)";
			Object params[] = { major.getName() };
			JdbcUtils.update(sql, params);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

	public List<Major> getAll(){
		String sql = "select id,name from major";
		List<Major> majors = JdbcUtils.getAllMajors(sql);
		return majors;
	}
}
