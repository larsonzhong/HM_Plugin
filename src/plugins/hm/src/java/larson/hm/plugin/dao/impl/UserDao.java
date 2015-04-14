package larson.hm.plugin.dao.impl;

import larson.hm.plugin.model.bean.User;
import larson.hm.plugin.util.JdbcUtils;
import larson.hm.plugin.util.MyException;

public class UserDao {

	/**
	 * @param user
	 */
	public void add(User user) {
		try {
			String sql = "insert into user(username,password,nickname,groups,phone,majorName,classes,type) values(?,?,?,?,?,?,?,?)";
			Object params[] = { user.getUsername(), user.getPassword(),
					user.getNickname(), user.getGroups(),user.getPhone(),user.getMajorName()
					,user.getClasses(),user.getType() };
			JdbcUtils.update(sql, params);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}


	/**
	 * 验证登陆
	 * 
	 * @param username
	 * @param password
	 * @param sdept
	 * @return
	 */
	public User validate(String username, String password, String majorName) {
		try {
			String sql = "select * from user where username="+username+" and password="+password+" and majorName='"+majorName+"'";
			return (User) JdbcUtils.validateQuery(sql);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

}
//public void update(User user) {
	// try {
	// String sql =
	// "update user set username=?,password=?,gender=?,nickname=? where id=?";
	// Object params[] = { user.getId(), user.getUsername(),
	// user.getPassword(), user.getNickname(), user.getId() };
	// JdbcUtils.update(sql, params);
	// } catch (Exception e) {
	// throw new DaoException(e);
	// }
	// }
	//
	// public void delete(String id) {
	// try {
	// String sql = "delete from user where id=?";
	// Object params[] = { id };
	// JdbcUtils.update(sql, params);
	// } catch (Exception e) {
	// throw new DaoException(e);
	// }
	// }
	//
	// public User find(String id) {
	// try {
	// String sql = "select * from user where id=?";
	// Object params[] = { id };
	// return (User) JdbcUtils.query(sql, params, new BeanHandler(
	// User.class));
	// } catch (Exception e) {
	// throw new DaoException(e);
	// }
	// }
	//
	// public User findByUserName(String username) {
	// try {
	// String sql = "select * from user where username=?";
	// Object params[] = { username };
	// return (User) JdbcUtils.query(sql, params, new BeanHandler(
	// User.class));
	// } catch (Exception e) {
	// throw new DaoException(e);
	// }
	// }