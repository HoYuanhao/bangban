/**
 * 创建日期：2016-12-20下午2:31:01
 * 修改日期：
 * 作者：邢传军
 * 目的：实现文件名的相关处理
 */
package com.xunyanhui.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.xunyanhui.bean.FileSaveRet;

public class FileUtil {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger("FileUtil");
	// 获取文件名
	public static String takeOutFileName(String filePath) {
		String fileName = filePath;
		if (null != filePath && !"".equals(filePath)) {
			int port = filePath.lastIndexOf("\\");
			if (port != -1) {
				fileName = filePath.substring(port + 1);
			}
		}
		return fileName;
	}
	//获取新的文件名
	public static String getNewFileName(String originalFileName, String uid,int type) {
		StringBuffer newFileName = new StringBuffer();
		if (null != originalFileName && !"".equals(originalFileName)) {
			int port = originalFileName.lastIndexOf(".");
			String typedefault = "jpg";
			if (port != -1) {
				typedefault = originalFileName.substring(port + 1);
			}
			StringBuffer suffix = new StringBuffer("");
			suffix.append(uid);
			//type=1表示文件名中是否添加时间戳
			if(type==1){
				suffix.append("_");
				suffix.append(Calendar.getInstance().getTimeInMillis());
			}
			newFileName.append(suffix);
			newFileName.append(".");
			newFileName.append(typedefault);
		}
		return newFileName.toString();
	}
	public static FileSaveRet saveAsBase64(String base64,List<String> path,String id){
		FileSaveRet ret = new FileSaveRet();
		ret.setCode(1);
		String dataPrix = "";
        String data = "";
		String base64decode = base64;
		if(base64decode == null || "".equals(base64decode)){
			ret.setCode(-2);
			ret.setMsg("上传失败，上传图片数据为空");
			System.out.println(ret.toString());
			
        }else{
            String [] d = base64decode.split("base64,");
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
                String suffix = "";
                if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                    suffix = ".jpg";
                } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                    suffix = ".ico";
                } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                    suffix = ".gif";
                } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                    suffix = ".png";
                }else{
                	ret.setCode(-4);
        			ret.setMsg("上传图片格式不合法");
                }
                System.out.println(ret.toString());
                if(ret.getCode()>0){
					String tempFileName = id + suffix;
					ret.setPic_extra(suffix);
					System.out.println("1"+ret.toString());
					try {
						byte[] ph = Base64Coder.decode(data);
						System.out.println(ph);
						for (int i = 0; i < ph.length; ++i) {
							if (ph[i] < 0) {// 调整异常数据
								ph[i] += 256;
							}
						}
						for (int i = 0; i < path.size(); i++) {
							// 保存图片
							String imgFilePath = path.get(i) + File.separator
									+ tempFileName;
							// 更新成功
							FileOutputStream out = new FileOutputStream(
									imgFilePath);
							out.write(ph);
							out.flush();
							out.close();
						}
						ret.setCode(1);
						ret.setMsg("保存成功！");
						System.out.println("2"+ret.toString());
					} catch (Exception e) {
						logger.error("file save as error" + e.getMessage());
					}
                }
            }else{
            	ret.setCode(-3);
    			ret.setMsg("上传失败，数据不合法");
            }
        }
		
		return ret;
	}

}
