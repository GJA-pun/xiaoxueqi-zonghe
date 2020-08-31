package com.oracle.gdms.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GoodsTypeEntiy implements Serializable{
	
	private int gtid;
	private String name;
	private String description;

	
	
	@Override
	public String toString() {
		return "GoodsTypeEntiy [gtid=" + gtid + ", name=" + name + ", description=" + description + "]";
	}
	public int getGtid() {
		return gtid;
	}
	public void setGtid(int gtid) {
		this.gtid = gtid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	
	
}
