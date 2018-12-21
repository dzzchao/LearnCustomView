package com.dzzchao.learncustomview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dzzchao.learncustomview.doubanhorizontal.DoubanHorizontalActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 豆瓣横向滑动
     *
     * @param view view
     */
    public void doubanHorizontal(View view) {
        startActivity(new Intent(this,DoubanHorizontalActivity.class));
    }
}
