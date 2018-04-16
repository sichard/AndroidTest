package com.example.androidtest.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class CustomView extends View {
	

	private Paint mPaint;

	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint = new Paint();
	}

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setColor(Color.RED);
		canvas.drawCircle(300, 500, 10, mPaint);
	}

}
