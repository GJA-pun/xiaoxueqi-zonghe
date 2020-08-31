package com.oracle.gdms.util;

import java.util.ResourceBundle;

public class Factory {

	private static ResourceBundle rb; //����һ����Դ�󶨶���
	
	static {
		rb = ResourceBundle.getBundle("config/application"); // �Զ�ƥ�� properties�ļ�
	}
	
	//���� (ȷ���ڴ���ֻ��Ψһһ��Factory����)
	//1�����췽��˽�л�
	private Factory() {}
	//2���Լ�����һ�� fac ����
	private static Factory fac;
	//3���ṩһ������������ȡ Factory ����
	public static Factory getInstance() {
		fac = fac == null ? new Factory() : fac;
		return fac;
	}
	
	public Object getObject(String key) {
		// ��ȡ�����ļ����������ļ����ҵ�key��Ӧ��class·��
		String className = rb.getString(key);
		// ����ִ��ʱװ����Ҫ����, 
		// ����key �� obj.properties �ļ���keyƥ�������ж�Ӧclass�ķ����new
		Object o = null;
		try {
			o = Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return o;
	}

}
