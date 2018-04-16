package com.example.androidtest.multipleviewpager;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @description 多页viewpage实现
 * @function 
 * @author caoshichao
 * @date [2014-12-17]
 */
public class MultiViewPagerActivity extends Activity {

	
    /** 缓存的最大页数 */
    private static int  TOTAL_COUNT = 3;
    
    /** viewpager的包装类 */
    private RelativeLayout viewPagerContainer;
    private ViewPager      viewPager;
    private TextView       indexText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_multi_fragment_demo);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        indexText = (TextView)findViewById(R.id.view_pager_index);
        viewPagerContainer = (RelativeLayout)findViewById(R.id.pager_layout);
        viewPager.setAdapter(new MyPagerAdapter());
        // to cache all page, or we will see the right item delayed
        viewPager.setOffscreenPageLimit(TOTAL_COUNT);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.dimen_12));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPagerContainer.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                return viewPager.dispatchTouchEvent(event);
            }
        });
        indexText.setText(new StringBuilder().append("1/").append(TOTAL_COUNT));
    }

    /**
     * this is a example fragment, just a imageview, You can replace it with your needs
     * 
     * @author Trinea 2013-04-03
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return TOTAL_COUNT;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MultiViewPagerActivity.this);
            imageView.setImageResource(R.drawable.image1 + position);
//            imageView.setScaleType(ScaleType.FIT_XY);
            ((ViewPager)container).addView(imageView, position);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((ImageView)object);
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            indexText.setText(new StringBuilder().append(position + 1).append("/").append(TOTAL_COUNT));
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // to refresh frameLayout
            if (viewPagerContainer != null) {
                viewPagerContainer.invalidate();
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }
}
