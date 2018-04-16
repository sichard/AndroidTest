package com.example.androidtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.androidtest.utils.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScreenPropertyActivity extends Activity {


	private TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_property);
		initView();
		test();
	}

	@SuppressLint("NewApi")
	private void initView() {
		mTextView = (TextView) findViewById(R.id.textView);
	}
	
//	private void performAnimate(final View target, final int start, final int end) {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
//
//        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
//
//            //持有一个IntEvaluator对象，方便下面估值的时候使用
//            private IntEvaluator mEvaluator = new IntEvaluator();
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//                //获得当前动画的进度值，整型，1-100之间
//                int currentValue = (Integer)animator.getAnimatedValue();
//                Log.i("current value: " + currentValue);
//
//                //计算当前进度占整个动画过程的比例，浮点型，0-1之间
//                float fraction = currentValue / 100f;
//
//                //这里我偷懒了，不过有现成的干吗不用呢
//                //直接调用整型估值器通过比例计算出宽度，然后再设给Button
//                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
//                target.requestLayout();
//            }
//        });
//
//        valueAnimator.setDuration(5000).start();
//    }

	private void test() {

		//		//声音大小
		//		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		//		//音乐音量  
		//
		//		int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//
		//		int current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		//
		//		Log.i("MUSIC", "max : " + max + " current : " + current);
		//		System.out.println("max : " + max + " current : " + current);
		//
		//屏幕宽高
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		int height = metric.heightPixels;   // 屏幕高度（像素）
		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
		
		StringBuilder sb = new StringBuilder();
		sb.append("width:");
		sb.append(width);
		sb.append("\n");
		sb.append("height:");
		sb.append(height);
		sb.append("\n");
		sb.append("density:");
		sb.append(density);
		sb.append("\n");
		sb.append("densityDpi:");
		sb.append(densityDpi);
		String dpiString = "";
		if (densityDpi / 160f < 1) {
			dpiString = String.valueOf(densityDpi / 160f);
		} else if (densityDpi / 160f == 1.5f) {
			dpiString = "hdpi";
		} else {
			switch (densityDpi / 160) {
				case 1:
					dpiString = "mdpi";
					break;
				case 2:
					dpiString = "xhdpi";
					break;
				case 3:
					dpiString = "xxdpi";
					break;
				case 4:
					dpiString = "xxxdpi";
					break;
				default:
					dpiString = "未知";

			}
		}
		sb.append(" = " + dpiString);
		sb.append("\n");
		sb.append("Model:");
		sb.append(Build.MODEL);
		sb.append("\n");
		sb.append("DEVICE:");
		sb.append(Build.DEVICE);
		sb.append("\n");
		sb.append("ID:");
		sb.append(Build.ID);
		sb.append("\n");
		sb.append("BRAND:");
		sb.append(Build.BRAND);
		sb.append("\n");
		sb.append("AndroidId:");
		sb.append(getAndroidID(this));
		
		mTextView.setText(sb.toString());

		Log.i("width:" + width);
		Log.i("height:" + height);
		Log.i("density:" + density);
		Log.i("densityDpi:" + densityDpi);
		//
		//		String aaa = "!@#$%^&&**(()&^%$12345\\\\\n*67832145122222222222222222222221114355555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555559/*\"";
		//		String ecodeString = null;
		//		try {
		//			ecodeString = Base64.encodeToString(aaa.getBytes("UTF-8"), Base64.NO_WRAP);
		//		} catch (UnsupportedEncodingException e) {
		//			e.printStackTrace();
		//		}
		//		System.out.println(ecodeString);
		//		try {
		//			System.out.println(new String(Base64.decode(ecodeString, Base64.NO_WRAP), "utf-8"));
		//		} catch (UnsupportedEncodingException e) {
		//			e.printStackTrace();
		//		}
		//				Log.i("csc", "=======aid:" + getAndroidID(getApplicationContext()));//aid:59546779eb026a48
//				Calendar calendar = Calendar.getInstance();
//				Log.i("--------------时间："+calendar.getTimeInMillis());
//				calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+90);
//				Log.i("--------------时间："+calendar.getTimeInMillis());
//				
//				
//				AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//				Intent intent = new Intent(ACTION_SCHEDULE_CHECK);
//				PendingIntent sender = PendingIntent.getBroadcast(this,0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
//				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
		
		createJson();
		
		String androidId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
		Log.i(androidId);
		
		getDefaultHome();
		
		Log.i("battery capacity:"+ getBatteryCapacity());
		
		testCalender();
		
//		Intent intent = new Intent(this, AnimationActivity.class);
//		ComponentName component = intent.getComponent();
//		Log.i(component.toString());

	}

	//	BroadcastReceiver mReceiver = new BroadcastReceiver() {
	//		
	//		@Override
	//		public void onReceive(Context context, Intent intent) {
	//			Log.i("csc", "--------------测试广播");
	//		}
	//	};

	public String getAndroidID(Context context) {
		String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		return androidId;
	}

	@Override
	protected void onDestroy() {
		//		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	private void createJson() {
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < 5; i++) {
			JSONObject object = new JSONObject();
			try {
				object.putOpt("test", i+"");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			array.put(object);
		}

		Log.i(array.toString());
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			try {
				if (i != 2) {
					JSONObject object = (JSONObject) array.get(i);
					list.add(object);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		array = new JSONArray(list);
//		JSONObject jsonObject = array.optJSONObject(2);
//		try {
//			jsonObject.putOpt("test", jsonObject.opt("test")+"###");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		Log.i(jsonObject);
		Log.i("length:"+array.length());
		Log.i(array.toString());
		
		
//		array.put("天河");
//		array.put("越秀");
//		array.put("番禺");
//		
//		Log.i(array.toString());
//		JSONObject jsonObject = new JSONObject();
//		try {
//			jsonObject.put("name", "广州");
//			jsonObject.put("市区", array);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		Log.i(jsonObject.toString());
//		
//		JSONArray array2 = (JSONArray) jsonObject.optJSONArray("市区");
//		for (int i = 0; i < array2.length(); i++) {
//			Log.i(array2.optString(i));
//		}
		
	}
	
	private String getDefaultHome() {
        PackageManager pkgManager = this.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		ResolveInfo ri = pkgManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY
				| PackageManager.GET_SHARED_LIBRARY_FILES);

		String packageName = ri.activityInfo.applicationInfo.packageName;
		return packageName;
    }
	
	public Double getBatteryCapacity() {

        Object mPowerProfile_ = null;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
        try {
            mPowerProfile_ = Class.forName(POWER_PROFILE_CLASS) .getConstructor(Context.class).newInstance(this);
        } catch (Exception e) {
            // Class not found?
            e.printStackTrace();
        }

        try {
            // Invoke PowerProfile method "getAveragePower" with param "battery.capacity"
            batteryCapacity = (Double) Class.forName(POWER_PROFILE_CLASS)
                    .getMethod("getAveragePower", java.lang.String.class)
                    .invoke(mPowerProfile_, "battery.capacity");
        } catch (Exception e) {
            // Something went wrong
            e.printStackTrace();
        }

        return batteryCapacity;
    }
	
	private void testCalender(){
		long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		int interval = calendar.get(Calendar.HOUR_OF_DAY)*3600*1000 + calendar.get(Calendar.MINUTE)*60*1000+calendar.get(Calendar.SECOND)*1000+calendar.get(Calendar.MILLISECOND);
		long zero = now - interval;
		Log.i("now:"+now);
		Log.i("interval:"+interval);
		Log.i("zero:"+zero);
	}
}
