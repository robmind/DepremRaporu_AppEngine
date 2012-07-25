package com.web.h3r3t1c.bugreport.server;

import java.io.IOException;
import java.util.Enumeration;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.web.h3r3t1c.bugreport.shared.ApplicationData;
import com.web.h3r3t1c.bugreport.shared.FetchHelper;

@SuppressWarnings("serial")
public class SubmitServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGetOrPost(req, resp);
	}

	// This method is called by the servlet container to process a POST request.
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGetOrPost(req, resp);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void doGetOrPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String dJson = "";
		try {
			Gson gson = new Gson();
			/*
			 * Enumeration enume = req.getParameterNames(); for (;
			 * enume.hasMoreElements(); ) { param = (String)enume.nextElement();
			 * }
			 */

			
			String value = req.getParameter("deprem");
			String value2 = req.getParameter("depremsize");
			if (value != null && !value.equals("")) {
				FetchHelper.getDepremData();
				if (ApplicationData.strLast24Hours.compareTo(value) == 0) {
					dJson = gson.toJson(ApplicationData.reportList);
				} else if (ApplicationData.strLast7Days.compareTo(value) == 0) {
					dJson = gson.toJson(ApplicationData.reportList);
				} else if (ApplicationData.strLast30Days.compareTo(value) == 0) {
					dJson = gson.toJson(ApplicationData.reportList);
				} else if (ApplicationData.strLast1Year.compareTo(value) == 0) {
					dJson = gson.toJson(ApplicationData.reportList);
				} else {
					dJson = gson.toJson(new String[] { "" });
				}
			}else if (value2 != null && !value2.equals("")){
				try {
					FetchHelper.getDepremDataBySize(Integer.parseInt(value2));
					dJson = gson.toJson(ApplicationData.lastDeprem);
				} catch (Exception e) {
					dJson = gson.toJson(new String[] { "" });
				}
				
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		ApplicationData.resetValue();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(dJson);
		//resp.getWriter().println(dJson);
	}
}
