package com.oracle.gdms.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.Model;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.service.impl.GoodsServiceImpl;
import com.oracle.gdms.util.Factory;

@Path("/goods")
public class GoodsRest {

	
	@Path("/push")
	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ResponseEntity pushGoods(Model goods) {
		
//		System.out.println("商品信息：" + goods.getGoods().getName() + goods.getGoods().getGtid());
		
		GoodsService service = new GoodsServiceImpl();
		int count = service.add(goods.getGoods());
		
		ResponseEntity resp = new ResponseEntity();
		resp.setCode(0);
		resp.setMessage("推送成功");
		
		return resp;
	}
	
	@Path("/update/type")
	@POST
	@Produces(MediaType.APPLICATION_JSON) // 规定接收的是json对象
	@Consumes(MediaType.APPLICATION_JSON) // 规定返回的结果为json对象
	public String updateGoodsType(String jsonparam) {
		JSONObject json = JSONObject.parseObject(jsonparam);
		String s_goodsid = json.getString("goodsid");
		String s_gtid = json.getString("gtid");
		
		int goodsid = Integer.parseInt(s_goodsid);
		int gtid = Integer.parseInt(s_gtid);
		
		GoodsService goodsService = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsEntity goods = new GoodsEntity();
		goods.setGtid(gtid);
		goods.setGoodsid(goodsid);
		int flag =  goodsService.upGoodsType(goods);
		
		JSONObject j = new JSONObject();
		if(flag == 1) {
			j.put("code", "1001");
			j.put("msg", "success");
		}else {
			j.put("code", "1002");
			j.put("msg", "error");
		}
		
		
		return j.toJSONString();
	}
	
}



