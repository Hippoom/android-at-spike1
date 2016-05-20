package com.deppon.app.june.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.util.Log;

/*
 * Sample1:
 * 		Logger.v("my message");
 * Sample2: 
 *  	String test = "aaa";
 *		try {
 *			test.substring(4, 12);
 *		} catch (Exception e) {
 *			Logger.e(e);
 *		}
 * Sample3:
 *  	String test = "aaa";
 *		try {
 *			test.substring(4, 12);
 *		} catch (Exception e) {
 *			Logger.e("my message", e);
 *		}
 */
public class Logger {

	public static final String LOG_TAG = "deppon";
	
	public static void v(String msg) {
		String strMsg = getPrefix(new Throwable()) + msg;
		Log.v(LOG_TAG, strMsg);
	}
	
	public static void e(String msg) {
		String strMsg = getPrefix(new Throwable()) + msg;
		Log.e(LOG_TAG, strMsg);
	}
	
	public static void e(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();

		String strMsg = getPrefix(new Throwable()) + sw.toString();
		Log.e(LOG_TAG, strMsg);
	}
	
	public static void e(String msg, Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();

		String strMsg = getPrefix(new Throwable()) + msg + "\n" + sw.toString();
		Log.e(LOG_TAG, strMsg);
	}
	
	private static String getPrefix(Throwable t) {
		return t.getStackTrace()[1].getClassName() + "."
				+ t.getStackTrace()[1].getMethodName() + " "
				+ t.getStackTrace()[1].getLineNumber() + ": ";
	}
}
