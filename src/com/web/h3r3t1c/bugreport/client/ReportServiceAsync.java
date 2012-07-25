package com.web.h3r3t1c.bugreport.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.web.h3r3t1c.bugreport.shared.ReportDataStore;

public interface ReportServiceAsync {

	void getReportData(AsyncCallback<ReportDataStore> callback);


}
