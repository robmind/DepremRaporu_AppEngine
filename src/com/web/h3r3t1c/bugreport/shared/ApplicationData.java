package com.web.h3r3t1c.bugreport.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.io.Serializable;

public class ApplicationData implements Serializable {
	private static final long serialVersionUID = 9045332374558591947L;
	public static String[] reportList;
	public static String[] pureBigData;
	
	public static String[] reportListBySize;
	public static String[] pureBigDataBySize;
	
	public static String[] last24Hours = new String[0];
	public static String[] last7Days = new String[0];
	public static String[] last30Days = new String[0];
	public static String[] last1Year = new String[0];
	
	public static String[] lastDeprem = new String[0];
	
	/*public static String[] last1Deprem = new String[0];
	public static String[] last3Deprem = new String[0];
	public static String[] last5Deprem = new String[0];
	public static String[] last10Deprem = new String[0];*/
	
	public static String strLast24Hours = "last24Hours";
	public static String strLast7Days = "last7Days";
	public static String strLast30Days = "last30Days";
	public static String strLast1Year = "last1Year";
	/*public static String strLast1Deprem = "last1Deprem";
	public static String strLast3Deprem = "last3Deprem";
	public static String strLast5Deprem = "last5Deprem";
	public static String strLast10Deprem = "last10Deprem";*/
	
	
	public static String[] mapTmpData;
	public static String[] mapFullData = new String[0];
	public static int depremMinSize = 3;
	//public static int depremMaxSize = 10;
	public static String xCord;
	public static String yCord;
	public static String gDate;
	public static String gWhere;
	public static boolean isClose = false;
	public static int whichList = 0;
	public static final int LANGUAGE_TURKISH = 0;
	public static final int LANGUAGE_ENGLISH = 1;
	public static String infoHtmlData = "";
	public static String telefonID = "";
	
	public static void resetValue (){
		reportList = null;
		pureBigData = null;
		reportListBySize = null;
		pureBigDataBySize = null;
		last24Hours = new String[0];
		last7Days = new String[0];
		last30Days = new String[0];
		last1Year = new String[0];
		lastDeprem = new String[0];
	}
}