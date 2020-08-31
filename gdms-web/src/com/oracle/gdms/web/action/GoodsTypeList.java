package com.oracle.gdms.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsTypeEntiy;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;


@WebServlet("/admin/goods/type/list.php")
public class GoodsTypeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		JSONObject json = new JSONObject();
		
		GoodsService goodsservice = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		 
		List<GoodsTypeEntiy> list= goodsservice.getGoodsTypes();
		
		json.put("list", list);
		
		response.getWriter().print(json.toJSONString());
	}



}
