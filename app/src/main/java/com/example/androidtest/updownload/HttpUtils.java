package com.example.androidtest.updownload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * <br>类描述:Http调用工具类
 * <br>功能详细描述:
 *
 * @author caoshichao
 * @date [2014-12-9]
 */
//CHECKSTYLE:OFF
public class HttpUtils {
	/** 默认为1,根据协议需要升级 */
	public static final int PVERSION = 1;
	
	/** 链接地址前缀 */
	public static final String URL_FINAL = "http://xxx/LLauDIYThemeStore/rest?method=";//正式服务器地址
	public static final String TYPE_UP_LOAD = "upload";
	public static final String TYPE_BROWSING = "browsing";
	
	
	/**
	 * <br>功能简述：获取请求的url
	 * <br>注意:
	 * @param type 请求类型
	 * @return
	 */
	public static String getUrl(String type) {
		return URL_FINAL + type;
	}
	
	/**
	 * <br>功能简述：生成协议头
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static JSONObject createPhead(Context context) {
		if (context == null) {
			return null;
		}
		JSONObject pHead = new JSONObject();
		try {
			pHead.put("p_version", PVERSION);// 协议版本号
			pHead.put("account_id",""); // TODO 账户id(此处需要传入google账户id)
			final WindowManager windowManager = (WindowManager) context.getSystemService(android.content.Context.WINDOW_SERVICE);
			final Display display = windowManager.getDefaultDisplay();
			final DisplayMetrics metrics = new DisplayMetrics();
			display.getMetrics(metrics);
			String dpi = metrics.widthPixels + "*" + metrics.heightPixels;
			pHead.put("dpi",dpi); // 手机分辨率（720*1280）
			pHead.put("go_id", ""); // go_id
			pHead.put("android_id",getAndroidID(context));// 手机androidID
			pHead.put("google_ad_id","");// google广告id；此处暂时传空，如需要可以通过getGAID()获取
			pHead.put("c_channel", getUidChannel(context));// 渠道号	
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0); // getPackageName()是你当前类的包名，0代表是获取版本信息
			int launcherVersion = packInfo.versionCode;
			pHead.put("c_version", String.valueOf(launcherVersion)); // 客户端软件版本号
			pHead.put("local",getLocal(context)); // 国家(大写:CN)
			pHead.put("sys_lang",Locale.getDefault().getLanguage().toLowerCase(Locale.getDefault())); //语言（小写:en）
			pHead.put("sys_sdk_level",Build.VERSION.SDK_INT); // 系统sdk版本(int)
			pHead.put("sys_os", Build.VERSION.RELEASE); //系统版本：4.1.2  
			pHead.put("model", Build.MODEL); // 手机型号，如Nexus 5 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pHead;
	}
	
	/**
	 * <br>功能简述：获取用户手机android_id
	 * <br>功能详细描述：
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static String getAndroidID(Context context){
		String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		return androidId;
	}
	
	/**
	 * <br>功能简述：获取国家大写
	 * <br>功能详细描述：
	 * <br>注意:
	 * @param context
	 * @return
	 */
	public static String getLocal(Context context) {
		String ret = null;
		try {
			TelephonyManager telManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (telManager != null) {
				ret = telManager.getSimCountryIso().toUpperCase(Locale.getDefault());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if (ret == null || ret.equals("")) {
			ret = Locale.getDefault().getCountry().toUpperCase(Locale.getDefault());
		}
		return null == ret ? "error" : ret;
	}
	
	/**
	 * <br>功能简述：获取 Google Advertising ID
	 * <br>功能详细描述：
	 * <br>注意:此处暂时不需要，后续若需要
	 * @param context
	 */
//	public void getGAID(final Context context) {
//
//		Thread thr = new Thread(new Runnable() {
//			@Override
//			public void run() {
//
//				try {
//					AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
//					String sAdId = adInfo.getId();
//				} catch (IOException e) {
//
//				} catch (GooglePlayServicesNotAvailableException e) {
//
//				} catch (IllegalStateException e) {
//					e.printStackTrace();
//				} catch (GooglePlayServicesRepairableException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		thr.start();
//	}
	
	/**
	 * <br>功能简述：从res/raw/uid.txt文件中获取渠道id
	 * <br>功能详细描述：
	 * <br>注意:
	 * @param context
	 * @param defaultUid
	 * @return
	 */
	public static String getUidChannel(Context context) {
		String uid = ""; 
		if (context == null) {
			return uid;
		}
		// 从资源获取流
		int resId = getRaw(context, "uid");
		if(0 == resId){
			return uid;
		}
		InputStream is = context.getResources().openRawResource(resId);
		try {
			byte[] buffer = new byte[64];
			int len = is.read(buffer); // 读取流内容
			if (len > 0) {
				uid = new String(buffer, 0, len).trim(); // 生成字符串
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return uid;
	}
	
	public static int getRaw(Context context, String resName){
		Resources res = context.getResources();
		return res.getIdentifier(resName, "raw", context.getPackageName());
	}
}
