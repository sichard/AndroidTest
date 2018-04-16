package com.example.androidtest.utils;

public class Log {
	public static void i(Object object) {
		android.util.Log.i("csc", object + "");
	}
	
	public static void e(Object object) {
		android.util.Log.e("csc", object + "");
	}
}
