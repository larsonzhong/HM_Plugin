package larson.hm.plugin.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import larson.hm.plugin.bean.Subject;
import larson.hm.plugin.dao.impl.SubjectDao;

public class GetSubjectService {

	public static String getSubjects() throws UnsupportedEncodingException {
			List<Subject> subjects = new SubjectDao().getAll();
			
			StringBuilder sb = new StringBuilder();
			
			//0。第一布要写消息头
			sb.append("<?xml version='1.0' encoding='UTF-8'?>");
			
			sb.append("<subjects>");
			for(Subject subject:subjects){
				
				sb.append("<subject>");
				
				sb.append("<id>");
				sb.append(subject.getId());
				sb.append("</id>");
				
				sb.append("<name>");
//				sb.append(new String(subject.getName().getBytes("utf-8"), "gbk"));
				sb.append(subject.getName());
				sb.append("</name>");
				
				sb.append("<majorName>");
//				sb.append(new String(subject.getMajorName().getBytes("utf-8"), "gbk"));
				sb.append(subject.getMajorName());
				sb.append("</majorName>");
				
				sb.append("</subject>");
			}
			sb.append("</subjects>");
		return sb.toString();
	}
}
