package com.oracle.gdms.util;

import java.util.ResourceBundle;

public class Factory {

	private static ResourceBundle rb; //定义一个资源绑定对象
	
	static {
		rb = ResourceBundle.getBundle("config/application"); // 自动匹配 properties文件
	}
	
	//单例 (确保内存中只有唯一一个Factory对象)
	//1、构造方法私有化
	private Factory() {}
	//2、自己创建一个 fac 对象
	private static Factory fac;
	//3、提供一个公共方法获取 Factory 对象
	public static Factory getInstance() {
		fac = fac == null ? new Factory() : fac;
		return fac;
	}
	
	public Object getObject(String key) {
		// 读取配置文件，从配置文件中找到key对应的class路径
		String className = rb.getString(key);
		// 程序执行时装载需要的类, 
		// 根据key 与 obj.properties 文件的key匹配来进行对应class的分配和new
		Object o = null;
		try {
			o = Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return o;
	}

}
