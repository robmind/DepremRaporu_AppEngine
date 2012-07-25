package com.web.h3r3t1c.bugreport.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;

public class FetchHelper {

	private static final String strUrl = "http://www.koeri.boun.edu.tr/scripts/lst6.asp";	
	
	public static HTTPRequest getDepremData(){
		HTTPRequest request = null;
		URL url;
		String[] pureBigData = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try {

			try {
				url = new URL(strUrl);
				request = new HTTPRequest(url, HTTPMethod.POST);
				in = new BufferedReader(new InputStreamReader(
						url.openStream(), "windows-1254"));
				String line1 = null;
				int inSize = 0;
				int inBigSize = 0;
				String[] pureData = new String[0];
				pureBigData = new String[0];
				String strLine;
				while ((line1 = in.readLine()) != null) {
					// SB.append(line1 + "\n");
					try {
						pureData = (String[]) expand(pureData);
						pureData[inSize] = line1;
						inSize++;
					} catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}
				}

				for (int i = 0; i < pureData.length; i++) {
					Pattern patternRecordID = Pattern
							.compile("(\\d{4})\\.(\\d{2})\\.(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})\\s+(\\d{2}\\.\\d{4})\\s+(\\d{2}\\.\\d{4})\\s+(\\d+\\.\\d)\\s+([\\d\\.[-]\\s]+)(.+)");
					Matcher matcherRecordID = patternRecordID
							.matcher(pureData[i]);

					while (matcherRecordID.find()) {
						String pData = matcherRecordID.group(1)
								+ "."
								+ matcherRecordID.group(2)
								+ "."
								+ matcherRecordID.group(3)
								+ " "
								+ matcherRecordID.group(4)
								+ ":"
								+ matcherRecordID.group(5)
								+ ":"
								+ matcherRecordID.group(6)
								+ "#"
								+ matcherRecordID.group(7)
								+ "#"
								+ matcherRecordID.group(8)
								+ "#"
								+ matcherRecordID.group(9)
								+ "#"
								+ getParsingData(matcherRecordID
										.group(10))
								+ "#"
								+ parseMockDataCity(matcherRecordID
										.group(11))[0];
						pureBigData = (String[]) expand(pureBigData);
						pureBigData[inBigSize] = pData;
						inBigSize++;
					}
				}
			} catch (Exception e) {
				System.out.println(">" + e.toString());
			} finally {

				try {
					if (isr != null)
						isr.close();
					if (in != null)
						in.close();
				} catch (IOException e) {
					System.out.println("2 " + e.toString());
				}
			}
			ApplicationData.reportList =  pureBigData;
		} catch (Exception e) {
			System.out.println("3 " + e.toString());
		}
		putDataList();
		return request;
	}
	
	public static HTTPRequest getDepremDataBySize(int lenOfDeprem){
		HTTPRequest request = null;
		URL url;
		String[] pureBigData = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try {

			try {
				url = new URL(strUrl);
				request = new HTTPRequest(url, HTTPMethod.POST);
				in = new BufferedReader(new InputStreamReader(
						url.openStream(), "windows-1254"));
				String line1 = null;
				int inSize = 0;
				int inBigSize = 0;
				String[] pureData = new String[0];
				pureBigData = new String[0];
				String strLine;
				while ((line1 = in.readLine()) != null) {
					// SB.append(line1 + "\n");
					try {
						pureData = (String[]) expand(pureData);
						pureData[inSize] = line1;
						inSize++;
					} catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}
				}

				for (int i = 0; i < pureData.length; i++) {
					Pattern patternRecordID = Pattern
							.compile("(\\d{4})\\.(\\d{2})\\.(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})\\s+(\\d{2}\\.\\d{4})\\s+(\\d{2}\\.\\d{4})\\s+(\\d+\\.\\d)\\s+([\\d\\.[-]\\s]+)(.+)");
					Matcher matcherRecordID = patternRecordID
							.matcher(pureData[i]);

					while (matcherRecordID.find()) {
						String pData = matcherRecordID.group(1)
								+ "."
								+ matcherRecordID.group(2)
								+ "."
								+ matcherRecordID.group(3)
								+ " "
								+ matcherRecordID.group(4)
								+ ":"
								+ matcherRecordID.group(5)
								+ ":"
								+ matcherRecordID.group(6)
								+ "#"
								+ matcherRecordID.group(7)
								+ "#"
								+ matcherRecordID.group(8)
								+ "#"
								+ matcherRecordID.group(9)
								+ "#"
								+ getParsingData(matcherRecordID
										.group(10))
								+ "#"
								+ parseMockDataCity(matcherRecordID
										.group(11))[0];
						pureBigData = (String[]) expand(pureBigData);
						pureBigData[inBigSize] = pData;
						inBigSize++;
					}
				}
			} catch (Exception e) {
				System.out.println(">" + e.toString());
			} finally {

				try {
					if (isr != null)
						isr.close();
					if (in != null)
						in.close();
				} catch (IOException e) {
					System.out.println("2 " + e.toString());
				}
			}
			ApplicationData.reportListBySize =  pureBigData;
		} catch (Exception e) {
			System.out.println("3 " + e.toString());
		}
		putDataListBySize(lenOfDeprem);
		return request;
	}
	
	public static String[] parseMockData(String str) {
		return str.split("[-][.][-][   ]");
	}

	public static String[] parseMockDataSec(String str) {
		return str.split("(\\d+\\.\\d)");
	}

	public static String[] parseMockDataCity(String str) {
		return str.split("(\\s{6})");
	}

	public static String[] parseMock(String str) {
		return str.split("[#]");
	}

	public static Object expand(Object a) {
		Class cl = a.getClass();

		if (!cl.isArray()) {
			return null;
		}

		int length = Array.getLength(a);
		int newLength = length + 1; // 50% more
		Class componentType = a.getClass().getComponentType();
		Object newArray = Array.newInstance(componentType, newLength);

		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}

	public static String getParsingData(String str) {
		String dd = str.substring(0, 15);
		String mock = "";
		try {
			String a = parseMockData(dd)[0].trim();
			String b = parseMockData(dd)[1].trim();
			String c = parseMockData(dd)[2].trim();
			if (a.compareTo("") != 0)
				mock = a + "#MD";
			else if (b.compareTo("") != 0)
				mock = b + "#ML";
			else if (c.compareTo("") != 0)
				mock = c + "#MS";
		} catch (Exception e) {
			String a = parseMockDataSec(dd)[0].trim();
			mock = a + "#ML";
		}

		return mock;
		// 2.6 2.5 -.-
	}
	
	public static void putDataList() {
		//GetReportListByDate(GetLastReportByEarthSize(ApplicationData.reportList));
		GetReportListByDate(ApplicationData.reportList);
	}

	public static void GetReportListByDate(String[] dateList) {
		DateConversionUtil dUtil = new DateConversionUtil();
		int l24 = 0, l7 = 0, l30 = 0, l1 = 0;
		int index = 0;
		if (ApplicationData.last24Hours.length < 1
				&& ApplicationData.last7Days.length < 1
				&& ApplicationData.last30Days.length < 1
				&& ApplicationData.last1Year.length < 1) {

			for (String dateStr : dateList) {
				Date dTime = dUtil.ConvertStringToDatetime(dateStr);
				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(dUtil.CurrentDate());
				calendar1.setTime(dTime);
				long milliseconds1 = calendar1.getTimeInMillis();
				long milliseconds2 = calendar2.getTimeInMillis();
				long diff = milliseconds2 - milliseconds1;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 1) {
					ApplicationData.last24Hours = dUtil
							.expanded(ApplicationData.last24Hours);
					ApplicationData.last24Hours[l24] = dateList[index] + "";
					l24++;
				}
				if (diffDays < 8) {
					ApplicationData.last7Days = dUtil
							.expanded(ApplicationData.last7Days);
					ApplicationData.last7Days[l7] = dateList[index] + "";
					l7++;
				}
				if (diffDays < 32) {
					ApplicationData.last30Days = dUtil
							.expanded(ApplicationData.last30Days);
					ApplicationData.last30Days[l30] = dateList[index] + "";
					l30++;
				}
				if (diffDays < 365) {
					ApplicationData.last1Year = dUtil
							.expanded(ApplicationData.last1Year);
					ApplicationData.last1Year[l1] = dateList[index] + "";
					l1++;
				}
				// System.out.println("Time in days: " + diffDays + " days.");
				index++;
			}
		}
		String str = "";
	}

	public static void putDataListBySize(int lenOfDeprem) {
		//GetReportListByDateBySize(GetLastReportByEarthSize(ApplicationData.reportListBySize), lenOfDeprem);
		GetReportListByDateBySize(ApplicationData.reportListBySize, lenOfDeprem);
	}

	public static void GetReportListByDateBySize(String[] dateList, int lenOfDeprem) {
		DateConversionUtil dUtil = new DateConversionUtil();
		int l24 = 0, l7 = 0, l30 = 0, l1 = 0;
		if (ApplicationData.lastDeprem.length < 1) {
			for (int i = 0; i < lenOfDeprem; i++) {
				ApplicationData.lastDeprem = dUtil.expanded(ApplicationData.lastDeprem);
				ApplicationData.lastDeprem[i] = dateList[i] + "";
			}
		}
		String str = "";
	}
	
	public static String[] GetLastReportByEarthSize(String[] reportList) {
		int l24 = 0;
		int index = 0;
		DateConversionUtil dUtil = new DateConversionUtil();
		// String[] tempReportList = ApplicationData.reportList;
		String[] temp = new String[0];
		for (String dateStr : reportList) {
			try {
				Double diffSize = Double.parseDouble(parseMock(dateStr)[4]);
				if (diffSize >= ApplicationData.depremMinSize) {
					temp = dUtil.expanded(temp);
					temp[l24] = reportList[index] + "";
					l24++;
				}
				index++;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		ApplicationData.reportList = temp;
		return temp;
	}

	static class DateConversionUtil {
		public Object resizeArray(Object oldArray, int newSize) {
			int oldSize = java.lang.reflect.Array.getLength(oldArray);
			Class elementType = oldArray.getClass().getComponentType();
			Object newArray = java.lang.reflect.Array.newInstance(elementType,
					newSize);
			int preserveLength = Math.min(oldSize, newSize);
			if (preserveLength > 0)
				System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
			return newArray;
		}

		public String[] expanded(String[] array) {
			int size = array.length + 1;
			String[] temp = new String[size];
			System.arraycopy(array, 0, temp, 0, array.length);
			for (int j = array.length; j < size; j++)
				temp[j] = "";
			return temp;
		}

		public Date ConvertStringToDatetime(String strDate) {
			DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			Date result = null;
			try {
				result = formatter.parse(strDate);
			} catch (ParseException e) {
				//
			}
			return result;
		}

		public Date CurrentDate() {
			DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			Date date = new Date();
			return ConvertStringToDatetime(dateFormat.format(date));
		}
	}
}