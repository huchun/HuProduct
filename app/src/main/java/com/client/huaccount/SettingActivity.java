package com.client.huaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.client.huaccount.base.BaseActivity;

/**
 * Created by l on 2018/7/18.
 */

public class SettingActivity extends BaseActivity {

    private LinearLayout   mLayout_about_html = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);

        mLayout_about_html = findViewById(R.id.about_html);

        initListener();
    }

    private void initListener() {
        mLayout_about_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,  AboutHtmlActivity.class);
                startActivity(intent);
            }
        });
    }
}
