package com.web.h3r3t1c.bugreport.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.web.h3r3t1c.bugreport.client.ReportService;
import com.web.h3r3t1c.bugreport.shared.Report;
import com.web.h3r3t1c.bugreport.shared.ReportDataStore;

public class ReportServiceImpl extends RemoteServiceServlet implements ReportService{

	@Override
	public ReportDataStore getReportData() {
		ReportDataStore data = new ReportDataStore();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query query = pm.newQuery(ReportData.class);
		List<ReportData>in = (List<ReportData>) query.execute();
		for(ReportData r:in)
		{
			data.add(new Report(r.getType(),r.getEmail(),r.getData(),r.getUuid(),r.getApp()));
		}
		pm.close();
		return data;
	}
}
