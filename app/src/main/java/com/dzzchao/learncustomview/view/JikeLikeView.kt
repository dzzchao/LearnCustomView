package com.dzzchao.learncustomview.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import com.dzzchao.learncustomview.App
import com.dzzchao.learncustomview.R

/**
 * 左边的点赞，还有右边的文字
 */
class JikeLikeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var likeNumber: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    private var isLike: Boolean = false
    private var drawablePadding: Float = 0F
    private lateinit var jikeIconView: JikeIconView
    private lateinit var jikeTextView: JikeTextView


    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.JikeLikeView)
        drawablePadding = typeArray.getDimension(R.styleable.JikeLikeView_drawable_padding, 0F)
        likeNumber = typeArray.getInt(R.styleable.JikeLikeView_like_number, 0)
        typeArray.recycle()


        removeAllViews()
        clipChildren = false
        orientation = LinearLayout.HORIZONTAL
        addLikeIconView()
        addLikeTextView()

    }

    private fun addLikeTextView() {
        TODO("Not yet implemented")
    }


    private fun addLikeIconView() {
//        jikeIconView = JikeIconView(context, null, 0)
//        jikeIconView.isIconLike = isLike
//        addView(jikeIconView, getLikeParams())
    }

//    private fun getLikeParams(): LayoutParams {
//        val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        if (mTopMargin < 0) {
//            params.topMargin = mTopMargin
//            //设置这个距离是为了文字与拇指居中显示
//        }
//        params.leftMargin = paddingLeft
//        params.topMargin += paddingTop
//        params.bottomMargin = paddingBottom
//        return params
//    }


    override fun onDraw(canvas: Canvas?) {

    }
}


fun Int.dp2px(): Float {
//        val scale = Resources.getSystem().displayMetrics.density
//        return this * scale + 0.5F
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            App.aContext.resources.displayMetrics)
}

fun Int.px2dp(): Float {
    val scale = App.aContext.resources.displayMetrics.density
    return this * scale + 0.5F
}

fun Int.sp2px(): Float {
    val fontScale = App.aContext.resources.displayMetrics.scaledDensity
    return this * fontScale + 0.5f
}