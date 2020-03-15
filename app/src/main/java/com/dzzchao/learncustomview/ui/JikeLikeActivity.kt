package com.dzzchao.learncustomview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dzzchao.learncustomview.R
import kotlinx.android.synthetic.main.activity_jike_like.*

class JikeLikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jike_like)


        jikelikeview.setOnClickListener {
            jikelikeview.isIconLike = !jikelikeview.isIconLike
        }
    }
}
