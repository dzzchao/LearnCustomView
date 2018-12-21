package com.dzzchao.learncustomview.doubanhorizontal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dzzchao.learncustomview.BaseActivity;
import com.dzzchao.learncustomview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 豆瓣横滑View
 */
public class DoubanHorizontalActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_horizontal);


        HorizontalScrollView scrollView = findViewById(R.id.scroll_view);

        RecyclerView mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("");
        }
        CardAdapter mAdapter = new CardAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    class CardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CardAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
