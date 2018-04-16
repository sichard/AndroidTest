//package com.example.androidtest.imageloader;
//
//import net.tsz.afinal.FinalBitmap;
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Environment;
//import android.widget.GridView;
//
//import com.example.androidtest.R;
//
//public class ImageLoadAcitivity extends Activity {
//	
//	private GridViewAdapter mAdapter;
//	private FinalBitmap mFinalBitmap;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_imageload);
//		GridView gridView = (GridView) findViewById(R.id.gridview);
//		mFinalBitmap = FinalBitmap.create(this);//初始化FinalBitmap模块
//		mFinalBitmap.configLoadingImage(R.drawable.ic_launcher);
//		mFinalBitmap.configLoadfailImage(R.drawable.part);
//		mAdapter = new GridViewAdapter(this, mFinalBitmap);
//		gridView.setAdapter(mAdapter);
//        
//	}
//}
