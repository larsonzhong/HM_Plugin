package larson.hm.plugin.service.impl;

import java.util.List;

import larson.hm.plugin.dao.impl.MajorDao;
import larson.hm.plugin.model.bean.Major;

public class GetMajorService {

	public static String getMajors() {
			List<Major> majors = new MajorDao().getAll();
			
			StringBuilder sb = new StringBuilder();
			
			//0。第一布要写消息头
			sb.append("<?xml version='1.0' encoding='UTF-8'?>");
			
			sb.append("<majors>");
			for(Major major:majors){
				
				sb.append("<major>");
				
				sb.append("<uid>");
				sb.append(major.getId());
				sb.append("</uid>");
				
				sb.append("<name>");
				sb.append(major.getName());
				sb.append("</name>");
				
				sb.append("</major>");
			}
			sb.append("</majors>");
		return sb.toString();
	}
}
