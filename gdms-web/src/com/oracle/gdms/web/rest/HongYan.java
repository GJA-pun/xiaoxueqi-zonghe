package com.oracle.gdms.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsTypeEntiy;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@Path("/hongyan")
public class HongYan {
	
	@Path("/sing")
	@GET
	public String sing() {
		System.out.println("����");
		return "hello";
	}
	
	@Path("/sing/{name}")
	@GET
	public String sing(@PathParam("name") String name) {
		System.out.print("����: " + name);
		return "����OK";
	}
	 
	@Path("/sing/ci")
	@GET
	public String singOne(@QueryParam("name") String name) {
		// sing/ci?name=
		System.out.print("���: " + name);
		return "���OK";
	}
	
	@Path("/push/one")
	@POST
	public String push(@FormParam("name") String name, @FormParam("sex") String sex) {
		System.out.print("��Ʒ����: " + name);
		return "form";
	}
	
	@Path("/push/json")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String pushJson(String jsonparam) {
		System.out.print(jsonparam);
		JSONObject json = JSONObject.parseObject(jsonparam);
		String name = json.getString("name");
		String type = json.getString("type");
		int age = json.getInteger("age");
		return "name=" + name +"type=" + type + "age=" + age;
	}
	
	@Path("/push/update/type")
	@POST
	@Produces(MediaType.APPLICATION_JSON) // �涨���յ���json����
	@Consumes(MediaType.APPLICATION_JSON) // �涨���صĽ��Ϊjson����
	public String updateGoodsType(String jsonparam) {
		System.out.print("updatGoodsType" + jsonparam);
		JSONObject json = JSONObject.parseObject(jsonparam);
		String goodsid = json.getString("goodsid");
		String gtid = json.getString("gtid");
		
		return jsonparam;
	}
	
	@Path("/push/get/type")
	@POST
	@Produces(MediaType.APPLICATION_JSON) // �涨���յ���json����
	@Consumes(MediaType.APPLICATION_JSON) // �涨���صĽ��Ϊjson����
	public List<GoodsEntity> getGoodsByGtid(GoodsTypeEntiy goodstype) { 
		GoodsService goodsservice = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		List<GoodsEntity> listgoods = goodsservice.getGoodsByGtid(goodstype);

		return listgoods;
	}
	
	
	@Path("/goods/push/one")
	@POST
	@Produces(MediaType.APPLICATION_JSON) // �涨���յ���json����
	@Consumes(MediaType.APPLICATION_JSON) // �涨���صĽ��Ϊjson����
	public ResponseEntity pushGoodsOne(String jsonparm) {
		ResponseEntity responseEntity = new ResponseEntity();
		
		try {
			JSONObject j = JSONObject.parseObject(jsonparm);
			String gs = j.getString("goods");
			GoodsModel goods = JSONObject.parseObject(gs, GoodsModel.class);
			System.out.println("�������յ���....");
			System.out.println("goodsid = " + goods.getGoodsid());
			System.out.println("goodsname = " + goods.getName());
			
			responseEntity.setCode(0);
			responseEntity.setMessage("���ͳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity.setCode(1184);
			responseEntity.setMessage("����ʧ��:���ݲ��Ϸ�:" + jsonparm);
		}
		
		return responseEntity;
		
	}
	
}
