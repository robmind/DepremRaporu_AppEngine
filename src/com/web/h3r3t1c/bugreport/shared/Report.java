package com.web.h3r3t1c.bugreport.shared;

import java.io.Serializable;


public class Report implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6938152367057417602L;

	private String type;

	private String email;

	private String data;

	private String uuid;

	private String app;
	
	public Report()
	{
		
	}
	
	public Report(String t,String e,String d,String u,String a)
	{
		type = t;
		email = e;
		data = d;
		uuid = u;
		app = a;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getApp() {
		return app;
	}
}
