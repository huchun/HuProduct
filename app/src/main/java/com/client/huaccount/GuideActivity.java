package com.client.huaccount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.client.huaccount.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 2018/7/28.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener{
   private static final String TAG = "GuideActivity";

   private ViewPager  mViewPager;
   private WelcomeAdapter  pagerAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_button:
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                this.startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = getWindow();
        window.setFlags(flag, flag);
        setContentView(R.layout.layout_guide);
        mViewPager = findViewById(R.id.viewpager);
        pagerAdapter = new WelcomeAdapter(getApplicationContext(), this);
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    public class WelcomeAdapter extends PagerAdapter{

        private int[] IMAGES = new int[]{R.mipmap.welcome1, R.mipmap.welcome2};
        private List<View>  viewList = new ArrayList<>();

        @SuppressLint("ResourceType")
        public WelcomeAdapter(Context context, View.OnClickListener clickListener) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View  view = null;
            ImageView imageView = null;
            for (int i = 0;i<2; i++){
                view = inflater.inflate(R.layout.weclome_item, null);
                imageView = view.findViewById(R.id.guide_image_item);
                imageView.setBackgroundResource(IMAGES[i]);
                view.setId(i);
                viewList.add(view);
            }
            view = inflater.inflate(R.layout.weclome_startpager, null);
            view.setId(2);
            ImageButton startButton = view.findViewById(R.id.start_button);
            startButton.setOnClickListener(clickListener);
            viewList.add(view);
        }

        @Override
        public int getCount() {
            return (IMAGES.length)+1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(getItemView(position));
            return getItemView(position);
        }

        private View getItemView(int position) {
            return viewList == null ? null: viewList.get(position);
        }
    }
}
