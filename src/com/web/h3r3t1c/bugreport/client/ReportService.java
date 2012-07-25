package com.web.h3r3t1c.bugreport.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.web.h3r3t1c.bugreport.shared.ReportDataStore;

@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService{
	
	ReportDataStore getReportData();
}
