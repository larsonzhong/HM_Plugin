package larson.hm.plugin.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import larson.hm.plugin.bean.Homework;
import larson.hm.plugin.dao.impl.HomeworkDao;

/**
 * 处理客户端发送过来数据的类
 * 
 * @author Larson
 * 
 */
public class PublishResultTool {
	private Homework homework;

	/**
	 * 让servlet拿到homework对象，然后修改图片和音频路径之后便于存储
	 * 
	 * @return
	 */
	public Homework getHomework() {
		return homework;
	}

	/**
	 * 从客户端读取上传的语音文件，这里我另外写了一个servlet来上传
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public String readVoice(InputStream in) {
		try {
			inputStream2File(in, homework.getVoiceUrl());
			if (homework.getImageUrl() != null
					&& homework.getImageUrl().length() > 0) {
				return Constant.RESULT_NEED_IMAGE;
			} else
				return Constant.PUBLISH_COMPLETE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.PUBLISH_FAILED;
		}
	}

	/**
	 * 从客户端读取上传的图片文件，这里我另外写了一个servlet来上传
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public String readImage(InputStream in) {
		try {
			inputStream2File(in, homework.getImageUrl());
			return Constant.PUBLISH_COMPLETE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.PUBLISH_FAILED;
		}
	}

	/**
	 * 流转化成文件
	 * 
	 * @param is
	 * @param savePath
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public static void inputStream2File(InputStream is, String savePath)
			throws Exception {
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1) {
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();

	}
 
	/**
	 * 读取作业信息并保存到数据库
	 * 
	 * @param request
	 * @param upload_path 
	 */
	public String readHomework(HttpServletRequest request, String upload_path) {
		String result = null;
		try {
			homework = new Homework();
			
			// 1。获取到数据
			String subject = new String(request.getParameter("subject").trim().getBytes("iso-8859-1"), "utf-8");
			homework.setSubject(subject);
			
			String type = new String(request.getParameter("type").trim().getBytes("iso-8859-1"), "utf-8");
			homework.setType(type);
			
			String limitTime = request.getParameter("limitTime").trim();
			homework.setLimitTime(limitTime);
			
			String time = request.getParameter("publishTime").trim();
			Long publishTime = Long.parseLong(time);
			homework.setPublishTime(publishTime);
			
			String describe = new String(request.getParameter("describe").trim().getBytes("iso-8859-1"), "utf-8");
			homework.setDescribe(describe);
			
			String voiceUrl = request.getParameter("voiceUrl").trim();
			if(voiceUrl!=null&&voiceUrl.length()>0){
				String voiceName = voiceUrl.substring(voiceUrl.lastIndexOf("/") + 1);
				homework.setVoiceUrl(upload_path+"\\audio\\"+voiceName);
			}
			System.out.println(homework.getVoiceUrl()+"---------------publishResultTool---129");
			
			String imageUrl = request.getParameter("imageUrl").trim();
			if(imageUrl!=null&&imageUrl.length()>0){
				String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
				homework.setImageUrl(upload_path+"\\image\\"+imageName);
			}
			System.out.println(homework.getImageUrl()+"---------------publishResultTool---129");
			
			saveToSql(homework);
			System.out.println(homework.toString());

			// 4.完成后给客户端反馈消息
			if (homework.getVoiceUrl() != null
					&& homework.getVoiceUrl().length() > 0)
				result = Constant.RESULT_NEED_VOICE;
			else
				result = Constant.PUBLISH_COMPLETE;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = Constant.PUBLISH_FAILED;
		}
		return result;
	}


	/**
	 * 把发布的作业信息保存到数据库
	 * 
	 * @param homework2
	 */
	private void saveToSql(Homework homework2) {
		HomeworkDao dao = new HomeworkDao();
		dao.add(homework);
	}

}
