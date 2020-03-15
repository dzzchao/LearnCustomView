package com.dzzchao.learncustomview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 *
 * 数字分为三部分，不变的数字，变化前的数字，变化后的数字
 * 为变化的数字增加动画
 *
 *  先实现无动画版本
 *
 */
class JikeTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    private var numberArray: IntArray = IntArray(3)

    private var numberText = 0
        set(value) {
            field = value
            invalidate()
        }


    private var pointF = PointF()

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            this.drawText(numberText.toString(), pointF.x, pointF.y, paint)
        }



    }

}