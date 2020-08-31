package com.oracle.gdms.web.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@WebServlet("/admin/goods/update.php")
public class GoodsUpDate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		String spec = request.getParameter("spec");
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");
		
		//去除 ￥ 符号
		int i = price.indexOf("￥");
		price = price.substring(i+1);
		
		GoodsEntity goods = new GoodsEntity();
		goods.setGoodsid(Integer.parseInt(goodsid));
		goods.setName(name);
		goods.setSpec(spec);
		goods.setPrice(Float.parseFloat(price));
		goods.setAmount(Float.parseFloat(amount));
		
		GoodsService goodsservice = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		int count = goodsservice.update(goods);
		response.setContentType("application/json;charset=utf-8");
		JSONObject j = new JSONObject();
		if (count == 1) {
			j.put("code",0);
			j.put("msg","success");
		}else{
			j.put("code",1005);
			j.put("msg","更新商品失败");
		}
		
		response.getWriter().print(j.toJSONString());;
	}

}
