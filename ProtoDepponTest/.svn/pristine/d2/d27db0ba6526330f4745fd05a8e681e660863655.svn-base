package com.deppon.app.june.util;

public class Timer {
	
	public static long WAIT_TIME = 20000L;
	private static long startMili = 0L;
	private static long endMili = 0L;
	
	public static void start() {
		startMili = System.currentTimeMillis();
	}
	
	public static boolean timeout() {
		endMili = System.currentTimeMillis();
		if ((endMili - startMili) > WAIT_TIME) {
			return true;
		}
		return false;
	}
	
	public static long passTime() {
		endMili = System.currentTimeMillis();
		return endMili - startMili;
	}
}