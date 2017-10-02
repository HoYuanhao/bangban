package com.xunyanhui.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

@Service
public class SmsSendService {
	private static String SMS_ACCOUNT = "18846196394";
	private static String SMS_PASSWORD = "kexin1996";

	public boolean send(String mobile, String code) {
		try {
			String path = "http://sms.tehir.cn/code/sms/api/v1/send?srcSeqId=123&account="
					+ SMS_ACCOUNT
					+ "&password="
					+ SMS_PASSWORD
					+ "&mobile="
					+ mobile + "&code=" + code + "&time=1";

			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();

			byte[] data = readInputStream(inStream);
			String result = new String(data, "UTF-8");
			JSONObject j = JSONObject.fromObject(result);
			if (j.get("responseCode").equals("0")) {
				return true;
			}
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getCode(String mobile) {
		String code = null;
		try {
			String path = "http://sms.tehir.cn/code/rcode/v1/get?account="
					+ SMS_ACCOUNT + "&password=" + SMS_PASSWORD + "&mobile="
					+ mobile + "&expireTime=160&length=6";

			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();

			byte[] data = readInputStream(inStream);
			String result = new String(data, "UTF-8");

			System.out.println(result);
			JSONObject j = JSONObject.fromObject(result);
			if (j.get("flag").equals("0")) {
				code = (String) j.get("code");
				System.out.println(code + "获取到的验证码为");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return code;
	}

	public boolean verifyCode(String mobile, String code) {

		try {
			String path = "http://sms.tehir.cn/code/rcode/v1/verify?mobile="
					+ mobile + "&code=" + code;

			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();

			byte[] data = readInputStream(inStream);
			String result = new String(data, "UTF-8");
			JSONObject j = JSONObject.fromObject(result);
			if ((Integer) j.get("flag") == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String post(String url, String parameter, String type) {
		URL postUrl;
		String res = "";
		try {
			postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(type);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(parameter);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				res = res + line;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;
	}
}