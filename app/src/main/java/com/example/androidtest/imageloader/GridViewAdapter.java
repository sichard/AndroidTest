//package com.example.androidtest.imageloader;
//
//import net.tsz.afinal.FinalBitmap;
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//
//import com.example.androidtest.R;
//
//public class GridViewAdapter extends BaseAdapter {
//
//	private Context mContext;
//	private FinalBitmap mFinalBitmap;
//	
//	public GridViewAdapter(Context context, FinalBitmap finalBitmap) {
//		this.mContext = context;
//		this.mFinalBitmap = finalBitmap;
//	}
//
//	@Override
//	public int getCount() {
//		return Constants.IMAGES.length;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return Constants.IMAGES[position];
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//	
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ImageView iv;
//	    if(convertView == null){
//	        convertView = View.inflate(mContext,R.layout.listview_item_imageloader, null);
//	        iv = (ImageView) convertView.findViewById(R.id.item_image);
//	        iv.setScaleType(ScaleType.CENTER_CROP);
//	        convertView.setTag(iv);
//	    }else{
//	        iv = (ImageView) convertView.getTag();
//	    }
//	    //bitmap加载就这一行代码，display还有其他重载，详情查看源码
//	    mFinalBitmap.display(iv,Constants.IMAGES[position]);
//		return convertView;
//	}
//
//}
