package com.dzzchao.learncustomview

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dzzchao.learncustomview.doubanhorizontal.DoubanHorizontalActivity
import com.dzzchao.learncustomview.ui.JikeLikeActivity
import com.dzzchao.learncustomview.ui.MaterialTextActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 豆瓣横向滑动
     *
     * @param view view
     */
    fun doubanHorizontal(view: View?) {
        startActivity(Intent(this, DoubanHorizontalActivity::class.java))
    }

    fun jikeLike(view: View) {
        startActivity(Intent(this, JikeLikeActivity::class.java))
    }

    fun MaterialText(view: View) {
        startActivity(Intent(this, MaterialTextActivity::class.java))
    }


}