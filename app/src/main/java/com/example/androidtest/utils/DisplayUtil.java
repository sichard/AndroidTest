/*
 * 文 件 名:  DisplayUtil.java
 * 版    权:  3G
 * 描    述:  <描述>
 * 修 改 人:  caoshilei
 * 修改时间:  2014-9-2
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.androidtest.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * <br>类描述:屏幕显示相关参数获取工具类
 * <br>功能详细描述:
 * 
 * @author  caoshilei
 * @date  [2014-9-2]
 */
public class DisplayUtil {
    /** 尺寸单位类型：像素 */
    public static final int UNIT_TYPE_PX = TypedValue.COMPLEX_UNIT_PX;
    /** 尺寸单位类型：逻辑像素 */
    public static final int UNIT_TYPE_DIP = TypedValue.COMPLEX_UNIT_DIP;
	/** <br>功能简述:获取桌面高清图标逻辑密度
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static int getLauncherLargeIconDensity(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		return activityManager.getLauncherLargeIconDensity();
	}
	
	/** <br>功能简述:获取屏幕逻辑密度
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static float getDensity() {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return metrics.density;
	}

    /** <br>功能简述:获取整个屏幕的高度
     * <br>功能详细描述:包括状态栏，操作栏
     * <br>注意:
     * @return
     */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display defaultDisplay = wm.getDefaultDisplay();
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		int screenHeight = metrics.heightPixels;

		try {
			if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
				screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(
						defaultDisplay);
			} else if (Build.VERSION.SDK_INT >= 17) {

				android.graphics.Point realSize = new android.graphics.Point();
				Display.class.getMethod("getRealSize", android.graphics.Point.class).invoke(
						defaultDisplay, realSize);
				screenHeight = realSize.y;
			}
		} catch (Exception e) {
			Log.e(e.getMessage());
		}
		
		return screenHeight;
	} 
	
	/** <br>功能简述:获取整个屏幕宽度
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return metrics.widthPixels;
	}
	
	/** <br>功能简述:获取显示区域高度
	 * <br>功能详细描述:包括状态栏高度，但是不包括系统虚拟操作栏
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static int getDisplayHeight(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return metrics.heightPixels;
	}
	
	/** <br>功能简述:获取显示区域宽度
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return metrics.widthPixels;
	}
	
	/** <br>功能简述:获取状态栏高度
	 * <br>功能详细描述:非动态获取，即使状态栏隐藏，也能获取状态栏高度
	 * <br>注意:
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		java.lang.reflect.Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			Log.e(e.getMessage());
		}
		return statusBarHeight;
	}
	
	/**
	 * <br>功能简述: 通过反射获取状态栏的真正高度，这个函数无视是否隐藏状态栏，直接拉取系统的状态栏的高度
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param context
	 * @return
	 */
	private static int sStatusBarHeight = -1;
	public static int getStatusBarHeight2(Context context) {
		if (sStatusBarHeight >= 0) {
			return sStatusBarHeight;
		}
		
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
//			if (Machine.isMEIZU()) {
//				try {
//					field = c.getField("status_bar_height_large");
//				} catch (Throwable t) {
//					t.printStackTrace();
//				}
//			}
			if (field == null) {
				field = c.getField("status_bar_height");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (field != null && obj != null) {
			try {
				int id = Integer.parseInt(field.get(obj).toString());
				sStatusBarHeight = context.getResources().getDimensionPixelSize(id);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		
		int normalHeight = DrawUtils.dip2px(25);
		if (DeviceUtil.isTablet(context) && sStatusBarHeight > normalHeight) {
			sStatusBarHeight = 0;
		} else {
			if (sStatusBarHeight <= 0 || normalHeight > normalHeight * 2) {
				if (DrawUtils.sVirtualDensity != -1) {
					sStatusBarHeight = (int) (25 * DrawUtils.sVirtualDensity + 0.5f); 
				} else {
					sStatusBarHeight = normalHeight;
				}
			}
		}
		return sStatusBarHeight;
	}
	
	/** <br>功能简述:获取当前状态栏高度
	 * <br>功能详细描述:当状态栏隐藏时，当前状态栏高度为0
	 * <br>注意:
	 * @param activity
	 * @return
	 */
	public static int getCurrentStatusBarHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}
	
	public static int getNavigationBarHeight(Activity activity) {  
        Resources resources = activity.getResources();  
        int resourceId = resources.getIdentifier("navigation_bar_height",  
                "dimen", "android");  
        //获取NavigationBar的高度  
        int height = resources.getDimensionPixelSize(resourceId);  
        return height;  
    }  
	
    public static boolean hasDeviceHasNavigationBar(Context activity) {  
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar  
        boolean hasMenuKey = ViewConfiguration.get(activity)  
                .hasPermanentMenuKey();  
        boolean hasBackKey = KeyCharacterMap  
                .deviceHasKey(KeyEvent.KEYCODE_BACK);  
  
        if (!hasMenuKey && !hasBackKey) {  
            return true;  
        }  
        return false;  
    }  
	
	/** <br>功能简述:获取定义的像素尺寸
	 * <br>功能详细描述:根据单位类型获取定义的最终像素尺寸值
	 * <br>注意:
	 * @param unit
	 * @param value
	 * @return
	 */
	public static float getDimenPixelSize(int unit, float value) {
		switch (unit) {
			case UNIT_TYPE_DIP :
				return value * getDensity() + 0.5f;
			case UNIT_TYPE_PX :
			default : {
				return value;
			}
		}
	}
	
	/** <br>功能简述:获取定义的像素尺寸
	 * <br>功能详细描述:根据单位类型和指定的屏幕逻辑密度获取定义的最终像素尺寸值
	 * <br>注意:
	 * @param unit
	 * @param value
	 * @param density
	 * @return
	 */
	public static float getDimenPixelSize(int unit, float value, float density) {
		switch (unit) {
			case UNIT_TYPE_DIP :
				return value * density + 0.5f;
			case UNIT_TYPE_PX :
			default : {
				return value;
			}
		}
	}
	
	public static ScreenSizeConvertor getSizeConvertor(int srcWidth, int srcHeight,
			float srcDensity, int destWidth, int destHeight, float destDensity) {
		return new ScreenSizeConvertor(srcWidth, srcHeight, srcDensity, destWidth, destHeight,
				destDensity);
	}
	
	/**
	 * <br>类描述:屏幕尺寸转换器
	 * <br>功能详细描述:用于不同屏幕之间的尺寸值转换
	 * 
	 * @author  caoshilei
	 * @date  [2014-12-10]
	 */
	public static class ScreenSizeConvertor {
		/** 原屏幕宽度：像素 */
		private int mSrcWidth;
		/** 原屏幕高度：像素 */
		private int mSrcHeight;
		/** 原屏幕逻辑密度 */
		private float mSrcDensity;
		/** 目标屏幕宽度：像素 */
		private int mDestWidth;
		/** 目标屏幕高度：像素 */
		private int mDestHeight;
		/** 目标屏幕逻辑密度 */
		private float mDestDensity;

		/** x轴缩放比 */
		private float mScaleX = 1;
		/** y轴缩放比 */
		private float mScaleY = 1;
		/** 尺寸缩放比，取x,y轴缩放比中的较小值 */
		private float mScale = 1;
		public ScreenSizeConvertor(int srcWidth, int srcHeight, float srcDensity, int destWidth,
				int destHeight, float destDensity) {
			mSrcWidth = srcWidth;
			mSrcHeight = srcHeight;
			mSrcDensity = srcDensity;
			mDestWidth = destWidth;
			mDestHeight = destHeight;
			mDestDensity = destDensity;

			mScaleX = ((float) mDestWidth) / mSrcWidth;
			mScaleY = ((float) mDestHeight) / mSrcHeight;
			mScale = mScaleX < mScaleY ? mScaleX : mScaleY;
		}

		/** <br>功能简述:获取源屏幕逻辑密度
		 * <br>功能详细描述:
		 * <br>注意:
		 * @return
		 */
		public float getSrcDensity() {
			return mSrcDensity;
		}

		/** <br>功能简述:获取目标屏幕逻辑密度
		 * <br>功能详细描述:
		 * <br>注意:
		 * @return
		 */
		public float getDestDensity() {
			return mDestDensity;
		}

		/** <br>功能简述:屏幕坐标x转换
		 * <br>功能详细描述:
		 * <br>注意:
		 * @param x
		 * @return
		 */
		public int convertX(int x) {
			return (int) (x * mScaleX);
		}

		/** <br>功能简述:屏幕坐标y转换
		 * <br>功能详细描述:
		 * <br>注意:
		 * @param y
		 * @return
		 */
		public int convertY(int y) {
			return (int) (y * mScaleY);
		}

		/** <br>功能简述:宽度缩放
		 * <br>功能详细描述:
		 * <br>注意:
		 * @param width
		 * @return
		 */
		public int convertWidth(int width) {
			return (int) (width * mScale);
		}

		/** <br>功能简述:高度缩放
		 * <br>功能详细描述:
		 * <br>注意:
		 * @param height
		 * @return
		 */
		public int converHeight(int height) {
			return (int) (height * mScale);
		}
	}

}
