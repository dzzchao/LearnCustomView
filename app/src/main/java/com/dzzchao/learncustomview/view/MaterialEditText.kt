package com.dzzchao.learncustomview.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import com.dzzchao.learncustomview.R


class MaterialEditText(context: Context?, attrs: AttributeSet?) : android.support.v7.widget.AppCompatEditText(context, attrs) {

    private val TEXT_SIZE: Float = 12.dp2px()
    private val TEXT_MARGIN: Float = 8.dp2px()
    private val TEXT_VERTICAL_OFFSET = 22.dp2px()
    private val TEXT_HORIZONTAL_OFFSET = 5.dp2px()
    private val TEXT_ANIMATION_OFFSET = 16.dp2px()

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var floatingLabelShown = false
    private var floatingLabelFraction = 0f
    private var animator: ObjectAnimator? = null
    private var useFloatingLabel = false
    var backgroundPadding: Rect = Rect()


    init {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typedArray!!.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typedArray.recycle()
        paint.textSize = TEXT_SIZE
        onUseFloatingLabelChanged()
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false
                        getAnimator()!!.reverse()
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true
                        getAnimator()!!.start()
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun onUseFloatingLabelChanged() {
        background.getPadding(backgroundPadding)
        if (useFloatingLabel) {
            setPadding(paddingLeft, (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
        } else {
            setPadding(paddingLeft, backgroundPadding.top, paddingRight, paddingBottom)
        }
    }

    fun setUseFloatingLabel(useFloatingLabel: Boolean) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel
            onUseFloatingLabelChanged()
        }
    }

    private fun getAnimator(): ObjectAnimator? {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this@MaterialEditText, "floatingLabelFraction", 0f, 1f)
        }
        return animator
    }

    fun getFloatingLabelFraction(): Float {
        return floatingLabelFraction
    }

    fun setFloatingLabelFraction(floatingLabelFraction: Float) {
        this.floatingLabelFraction = floatingLabelFraction
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (0xff * floatingLabelFraction).toInt()
        val extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), TEXT_HORIZONTAL_OFFSET.toFloat(), TEXT_VERTICAL_OFFSET + extraOffset, paint)
    }

}