/**  
 * @author: caoshichao  
 * @date: 2015-2-13 下午4:52:22
 */
package com.example.androidtest.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;

import com.example.androidtest.R;
import com.example.androidtest.utils.Log;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * @类描述
 * @注意: Timer的定时不受系统时间的影响，即使更改了系统时间，Timer也要等到过了指定的时间间隔才会触发Timer任务。
 * @author caoshichao
 * @date [2015-2-13]
 */
public class TimerTestActivity extends Activity {
	private Handler mHandler;
	private TextView mTextView;
	protected SimpleDateFormat mDateFormat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_test);
		mTextView = (TextView) findViewById(R.id.textView1);
		mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		String time = mDateFormat.format(new Date());
		mTextView.setText(time);
		Log.i("start time:"+ time);
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				mTextView.setText((String) msg.obj);
				Log.i("time:"+ msg.obj);
			}
		};
		
		TimerTaskManager.scheduleTimerTask(new TimerTask() {
			
			@Override
			public void run() {
				Date date = new Date();
				Message message = Message.obtain();
				message.obj = mDateFormat.format(date);
				mHandler.sendMessage(message);
			}
		}, 60*1000, 60*1000);
	}
}
