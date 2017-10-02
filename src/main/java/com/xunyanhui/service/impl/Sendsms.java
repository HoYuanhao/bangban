package com.xunyanhui.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

@Service
public class Sendsms {

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	public boolean sendvc(int mobile_code, String phone) {
		boolean fudge = false;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = { // �ύ����
				new NameValuePair("account", "C06541722"), // �鿴�û������¼�û�����->��֤�롢֪ͨ����->�ʻ���ǩ������->APIID
				new NameValuePair("password", "0cedfbbd3922c22efcdc3f3fb72c8fb3"), // �鿴�������¼�û�����->��֤�롢֪ͨ����->�ʻ���ǩ������->APIKEY
				// new NameValuePair("password",
				// util.StringUtil.MD5Encode("����")),
				new NameValuePair("mobile", phone), new NameValuePair("content", content), };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			// System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			fudge = true;
			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if ("2".equals(code)) {
				System.out.println("短信提交成功");
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fudge;

	}

	public int getcode() {
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		return mobile_code;
	}

}