package com.dzzchao.learncustomview.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dzzchao.learncustomview.R
import timber.log.Timber


/**
 * 左边的大拇指效果分为三部
 * 1. 大拇指先放大后缩小在缩小
 * 2. 闪光是放大显示
 * 3. 圆圈是先出现然后放大然后消失
 *
 * 用法是放到大的view里面和数字一起使用，所以先不用设置attr
 *
 */
class JikeIconView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * 是否是点赞效果
     */
    public var isIconLike = false
        set(value) {
            field = value
            invalidate()
            //判断动画
            if (value) {
                //开始点赞动画
                startLikeAnim()
            } else {
                //开始取消动画
                startUnlikeAnim()
            }
        }

    //ANTI_ALIAS_FLAG - 抗锯齿
    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmapLike: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected)
    private var bitmapUnLike: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_unselected)
    private var bitmapShining: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected_shining)

    //设置一个中心点，我们从中心点坐标开始绘制，他的坐标，在当前view的宽高的中心点就可以。
    // 所有的坐标都以这个中心点为基准。
    private var startPoint: Point = Point()

    private val scaleMin = 0.9f
    private val scaleMiddle = 1.1f
    private val scaleMax = 1f

    init {

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Timber.d("onSizeChanged() w: $w h: $h oldw: $oldw oldh: $oldh ")
        startPoint.set(w / 2, h / 2)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        Timber.d("onDraw startPoint ${startPoint.x}  ${startPoint.y}")
        if (isIconLike) {
            canvas?.apply {
                val likeLift = startPoint.x.toFloat() - bitmapLike.width / 2
                val likeTop = startPoint.y.toFloat() - bitmapLike.height / 2
                this.drawBitmap(bitmapLike,
                        likeLift,
                        likeTop,
                        bitmapPaint)

                //TestCode  画出图片的边框
//                bitmapPaint.style = Paint.Style.STROKE
//                bitmapPaint.strokeWidth = 2F
//                this.drawRect(likeLift, likeTop, likeLift + bitmapLike.width, likeTop + bitmapLike.height,bitmapPaint)

                val shiningLift = startPoint.x.toFloat() - bitmapShining.width / 2
                val shiningTop = startPoint.x.toFloat() - bitmapShining.height / 2 - bitmapLike.height / 2
                this.drawBitmap(bitmapShining,
                        shiningLift,
                        shiningTop,
                        bitmapPaint)

                //TestCode  画出图片的边框
//                this.drawRect(shiningLift, shiningTop, shiningLift + bitmapShining.width,
//                        shiningTop + bitmapShining.height,bitmapPaint)
            }
        } else {
            canvas?.drawBitmap(bitmapUnLike,
                    startPoint.x.toFloat() - bitmapUnLike.width / 2,
                    startPoint.y.toFloat() - bitmapUnLike.height / 2,
                    bitmapPaint)
        }
    }


    private fun startLikeAnim() {
        //1. 大拇指先放大后缩小在缩小
        //改变宽高

        val likeAnimator = ObjectAnimator.ofFloat(this, "likeBitmap", scaleMin, scaleMiddle, scaleMax)
        likeAnimator.duration = 200
//        likeAnimator.start()
        //3. 圆圈是先出现然后放大然后消失

        //2.闪光
        val shiningAnimator = ObjectAnimator.ofFloat(this, "shiningAnim", 0.1F, 1F)
        shiningAnimator.duration = 400
//        shiningAnimator.start()

        val animatorSet = AnimatorSet()
        animatorSet.play(shiningAnimator).with(likeAnimator)
        animatorSet.start()
    }

    private fun setShiningAnim(scale: Float) {
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        bitmapShining = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected_shining)
        bitmapShining = Bitmap.createBitmap(bitmapShining, 0, 0, bitmapShining.width, bitmapShining.height,
                matrix, true)
        postInvalidate()
    }

    private fun startUnlikeAnim() {

    }

    private fun setLikeBitmap(scale: Float) {
        val matrix = matrix
        matrix.postScale(scale, scale)
        bitmapLike = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected)
        bitmapLike = Bitmap.createBitmap(bitmapLike, 0, 0,
                bitmapLike.width, bitmapLike.height,
                matrix, true)
        invalidate()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }
}