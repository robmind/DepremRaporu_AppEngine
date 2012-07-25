package com.web.h3r3t1c.bugreport.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportDataStore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8622969897264243873L;
	private List<Report>data;
	
	public ReportDataStore()
	{
		data = new ArrayList<Report>();
	}
	public void add(Report r)
	{
		data.add(r);
	}
	public List<Report> getData()
	{
		return data;
	}
}
