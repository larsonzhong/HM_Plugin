package larson.hm.plugin.handler;

import java.io.UnsupportedEncodingException;

import larson.hm.plugin.bean.Homework;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PublishHandler extends DefaultHandler {
	private Homework homework;
	private String var="";
	private String upload_path;
	
	
	public PublishHandler(String upload_path) {
		this.upload_path = upload_path;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//锟斤拷锟斤拷homework锟斤拷签锟酵憋拷示锟斤拷一锟斤拷homework锟斤拷锟斤拷
		if(qName.equals("homework"))
			homework = new Homework();
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		try {
			System.out.println(var+"--------");
			System.out.println(new String(var.getBytes("iso-8859-1"),"utf-8"));
			System.out.println(new String(var.getBytes("gbk"),"utf-8"));
			System.out.println(new String(var.getBytes("gb2312"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if("subject".equals(qName))
			homework.setSubject(var);
		if("type".equals(qName))
			homework.setType(var);
		if("limitTime".equals(qName))
			homework.setLimitTime(var);
		if("publishTime".equals(qName))
			homework.setPublishTime(Long.parseLong(var));
		if("describe".equals(qName))
			homework.setDescribe(var);
		if("voiceUrl".equals(qName)){
			if(var!=null&&var.length()>0)
				homework.setVoiceUrl(upload_path+"\\audio\\"+System.currentTimeMillis()+".amr");
		}
		if("imageUrl".equals(qName)){
			if(var!=null&&var.length()>0)
				homework.setImageUrl(upload_path+"\\image\\"+System.currentTimeMillis()+".jpg");
		}
		
		//锟斤拷锟斤拷锟斤拷锟斤拷时锟斤拷锟斤拷锟矫匡拷
		var="";
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		var+=new String(ch, start, length);
		super.characters(ch, start, length);
	}

	/**
	 * 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷缘锟紿omework锟斤拷锟斤拷
	 * @return
	 */
	public Homework getHomework() {
		return homework;
	}

}
