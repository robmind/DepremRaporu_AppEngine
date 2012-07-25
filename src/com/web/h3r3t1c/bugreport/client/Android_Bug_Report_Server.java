package com.web.h3r3t1c.bugreport.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.web.h3r3t1c.bugreport.shared.Report;
import com.web.h3r3t1c.bugreport.shared.ReportDataStore;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Android_Bug_Report_Server implements EntryPoint {

	private Image img;
	@Override
	public void onModuleLoad() {
		
		initLoading();
		ReportServiceAsync r = GWT.create(ReportService.class);
		r.getReportData(new AsyncCallback<ReportDataStore>(){

			@Override
			public void onFailure(Throwable caught) {
				Label l = new Label("An error has occured!<br>Please try again in a few seconds!"+caught.getMessage());
				RootPanel root = RootPanel.get("main");
				root.add(l);
			}

			@Override
			public void onSuccess(ReportDataStore result) {
				loadUI(result.getData());
			}
			
		});
	}
	private void initLoading()
	{
		img = new Image("img/loading7.gif");
		img.setSize("100px", "100px");
		RootPanel.get("main").add(img);
	}
	private void loadUI(List<Report>data)
	{
		
	    CellTable<Report>table = new CellTable<Report>();
	    TextColumn<Report> type = new TextColumn<Report>(){

			@Override
			public String getValue(Report o) {
				// TODO Auto-generated method stub
				return o.getType();
			}};
	    table.addColumn(type, "Report Type");
	    TextColumn<Report> email = new TextColumn<Report>(){

			@Override
			public String getValue(Report o) {
				// TODO Auto-generated method stub
				return o.getEmail();
			}};
	    table.addColumn(email, "Email");
	    TextColumn<Report> d = new TextColumn<Report>(){

			@Override
			public String getValue(Report o) {
				String s = o.getData();
				
				return s;
			}};
	    table.addColumn(d, "Report Data");
	    TextColumn<Report> uuid = new TextColumn<Report>(){

			@Override
			public String getValue(Report o) {
				// TODO Auto-generated method stub
				return o.getUuid();
			}};
	    table.addColumn(uuid, "UUID");
	    TextColumn<Report> app = new TextColumn<Report>(){

			@Override
			public String getValue(Report o) {
				// TODO Auto-generated method stub
				return o.getApp();
			}};
	    table.addColumn(app, "App");
	    
	    
	    ListDataProvider<Report>dp = new ListDataProvider<Report>();
	    dp.addDataDisplay(table);
	    dp.setList(data);
	    
		RootPanel root = RootPanel.get("main");
		root.remove(img);
		root.add(table);
	}
	
	
}
