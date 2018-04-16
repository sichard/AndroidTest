//package com.example.androidtest.updownload;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//import com.example.androidtest.R;
//
//import net.tsz.afinal.FinalHttp;
//import net.tsz.afinal.http.AjaxCallBack;
//import net.tsz.afinal.http.AjaxParams;
//import net.tsz.afinal.http.HttpHandler;
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.view.animation.AlphaAnimation;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class UpDoanLoadActivity extends Activity {
//
//	private TextView mTextView;
//	private Button mDownLoadButton,mUploadButton;
//	private ViewGroup mView;
//	private LayoutInflater mLayoutInflater;
//	private HttpHandler<File> handler;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		mLayoutInflater = LayoutInflater.from(this);
//		mView = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_up_down_load, null);
//		setContentView(mView);
//		initView();
//	}
//	
//	private void initView() {
//		mTextView = (TextView) findViewById(R.id.textView1);
//		mUploadButton = (Button) findViewById(R.id.stop);
//		mUploadButton.setText("上传");
//		mUploadButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
////				handler.stop();
//				upload();
//			}
//		});
//		mDownLoadButton = (Button) findViewById(R.id.button);
//		mDownLoadButton.setText("下载");
//		mDownLoadButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				handler = downLoad();
//			}
//
//		});
//	}
//	
//	private HttpHandler downLoad() {
//		FinalHttp fh = new FinalHttp();
//		//调用download方法开始下载
//		@SuppressWarnings("rawtypes")
////		HttpHandler handler = fh.download("http://godfs.3g.cn/dynamic/resdown/201312301412/gostore/gostore-1388383970472.apk?appid=17593&upload=999005&packagename=com.gau.go.launcherex.theme.Snewyear", //这里是下载的路径
//		HttpHandler handler = fh.download("http://godfs.3g.cn/dynamic/resdown/201311291628/gostore/gostore-1385713691247.apk?appid=17213&upload=999005&packagename=com.gau.go.launcherex.theme.swvedf.hhuivg.advanced", //这里是下载的路径
//				"/mnt/sdcard/testapk.apk", //这是保存到本地的路径
//				true,//true:断点续传 false:不断点续传（全新下载）
//				new AjaxCallBack<File>() {
//					@Override
//					public void onLoading(long count, long current) {
//						float percent = (float)current / count *100;
////						mTextView.setText("下载进度：" + current + "/" + count);
//						mTextView.setText("下载进度：" + percent + "%");
//					}
//
//					public void onSuccess(File t) {
//						mTextView.setText(t == null ? "null" : t.getAbsoluteFile().toString());
//					}
//
//				});
//
//		//调用stop()方法停止下载
//		return handler;
////		handler.stop();
//	}
//	
//	
//	private void upload() {
//		AjaxParams params = new AjaxParams();
//		params.put("username", "Sichard Cho");
//		params.put("password", "123456");
//		params.put("email", "test@tsz.net");
////		String fileName = Environment.getExternalStorageDirectory().getPath()+"/testapk.apk";
////		String fileName = Environment.getExternalStorageDirectory().getPath()+"/123.jpg";
//		String fileName = Environment.getExternalStorageDirectory().getPath()+"/360.apk";
//		Log.i("csc", fileName);
//		try {
//			params.put("profile_picture", new File(fileName));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		// 上传文件
//
//		FinalHttp fh = new FinalHttp();
//		fh.post("http://192.168.216.114:8080/UploadFile/servlet/UpFileServlet", params,
//				new AjaxCallBack<String>() {
//					@Override
//					public void onLoading(long count, long current) {
//						mTextView.setText(current + "/" + count);
//					}
//
//					public void onSuccess(String t) {
//						mTextView.setText(t == null ? "null" : t);
//					}
//
//					@Override
//					public void onFailure(Throwable t, int errorNo, String strMsg) {
//						super.onFailure(t, errorNo, strMsg);
//					}
//					
//				});
//		
//	}
//}
