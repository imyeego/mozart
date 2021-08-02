package com.imyeego.mozart

import android.graphics.*
import com.imyeego.mozart.ShadowDrawable
import androidx.core.view.ViewCompat
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View

class ShadowDrawable private constructor(
    target: View?,
    private val mShape: Int,
    bgColor: IntArray,
    borderWidth: Int,
    borderColor: Int,
    shapeRadius: Int,
    shadowColor: Int,
    shadowRadius: Int,
    offsetX: Int,
    offsetY: Int
) : GradientDrawable() {
    private val mShadowPaint: Paint
    private val mBgPaint: Paint
    private var mBorderPaint: Paint? = null
    private val mShadowRadius: Int
    private val mShapeRadius: Int
    private val mOffsetX: Int
    private val mOffsetY: Int
    private val mBgColor: IntArray?
    private val mBorderWidth: Int
    private val mBorderColor: Int
    private var mRect: RectF? = null
    private var mOldRect: RectF? = null
    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mRect = RectF(
            (left + mShadowRadius - mOffsetX).toFloat(),
            (top + mShadowRadius - mOffsetY).toFloat(),
            (right - mShadowRadius - mOffsetX).toFloat(),
            (bottom - mShadowRadius - mOffsetY).toFloat()
        )
        mOldRect = RectF(
            (left + mShadowRadius + mBorderWidth - mOffsetX).toFloat(),
            (top + mShadowRadius + mBorderWidth- mOffsetY).toFloat(),
            (right - mShadowRadius - mBorderWidth- mOffsetX).toFloat(),
            (bottom - mShadowRadius - mBorderWidth - mOffsetY).toFloat()
        )
    }

    override fun draw(canvas: Canvas) {
        if (mBgColor != null) {
            if (mBgColor.size == 1) {
                mBgPaint.color = mBgColor[0]
            } else {
                mBgPaint.shader = LinearGradient(
                    mRect!!.left, mRect!!.height() / 2, mRect!!.right,
                    mRect!!.height() / 2, mBgColor, null, Shader.TileMode.CLAMP
                )
            }
        }
        if (mShape == SHAPE_ROUND) {
            canvas.drawRoundRect(
                mRect!!,
                mShapeRadius.toFloat(),
                mShapeRadius.toFloat(),
                mShadowPaint
            )
            canvas.drawRoundRect(mRect!!, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mBgPaint)
            if (mBorderWidth > 0) {
                canvas.drawRoundRect(mOldRect!!, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mBorderPaint!!)
            }
        } else {
            canvas.drawCircle(
                mRect!!.centerX(),
                mRect!!.centerY(),
                Math.min(mRect!!.width(), mRect!!.height()) / 2,
                mShadowPaint
            )
            canvas.drawCircle(
                mRect!!.centerX(),
                mRect!!.centerY(),
                Math.min(mRect!!.width(), mRect!!.height()) / 2,
                mBgPaint
            )
            if (mBorderWidth > 0) {
                canvas.drawCircle(
                    mOldRect!!.centerX(),
                    mOldRect!!.centerY(),
                    Math.min(mOldRect!!.width(), mOldRect!!.height()) / 2,
                    mBorderPaint!!
                )
            }
        }
    }

    override fun setAlpha(alpha: Int) {
        mShadowPaint.alpha = alpha
    }


    override fun setColorFilter(colorFilter: ColorFilter?) {
        mShadowPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    class Builder {
        var mShape: Int
        var mShapeRadius: Int
        var mShadowColor: Int
        var mShadowRadius: Int
        var mOffsetX: Int
        var mOffsetY: Int
        var mBgColor: IntArray
        var target: View? = null
        var mBorderWidth: Int
        var mBorderColor: String
        fun setTarget(mTarget: View): Builder {
            this.target = mTarget
            return this
        }

        fun setBorderWidth(borderWidth: Int):Builder {
            this.mBorderWidth = borderWidth
            return this
        }

        fun setBorderColor(borderColor: String):Builder {
            this.mBorderColor = borderColor
            return this
        }
        fun setShape(mShape: Int): Builder {
            this.mShape = mShape
            return this
        }

        fun setShapeRadius(ShapeRadius: Int): Builder {
            mShapeRadius = ShapeRadius
            return this
        }

        fun setShadowColor(shadowColor: Int): Builder {
            mShadowColor = shadowColor
            return this
        }

        fun setShadowRadius(shadowRadius: Int): Builder {
            mShadowRadius = shadowRadius
            return this
        }

        fun setOffsetX(OffsetX: Int): Builder {
            mOffsetX = OffsetX
            return this
        }

        fun setOffsetY(OffsetY: Int): Builder {
            mOffsetY = OffsetY
            return this
        }

        fun setBgColor(BgColor: Int): Builder {
            mBgColor[0] = BgColor
            return this
        }

        fun setBgColor(BgColor: IntArray): Builder {
            mBgColor = BgColor
            return this
        }

        fun builder(): ShadowDrawable {
            return ShadowDrawable(
                target,
                mShape,
                mBgColor,
                mBorderWidth,
                Color.parseColor(mBorderColor),
                mShapeRadius,
                mShadowColor,
                mShadowRadius,
                mOffsetX,
                mOffsetY
            )
        }

        init {
            mShape = SHAPE_ROUND
            mShapeRadius = 0
            mShadowColor = Color.parseColor("#4d000000")
            mShadowRadius = 0
            mOffsetX = 0
            mOffsetY = 0
            mBgColor = IntArray(1)
            mBgColor[0] = Color.TRANSPARENT
            mBorderColor = "00ffffff"
            mBorderWidth = 0


        }
    }

    companion object {
        const val SHAPE_ROUND = 1
        const val SHAPE_CIRCLE = 2
        fun setShadowDrawable(view: View, drawable: Drawable?) {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }

        fun setShapeRadius(view: View, radius: Int) {
            val drawable = Builder()
                    .setShapeRadius(radius)
                    .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)

        }

        /**
         * 为指定View添加阴影
         * @param view 目标View
         * @param shapeRadius View的圆角
         * @param shadowColor 阴影的颜色
         * @param shadowRadius 阴影的宽度
         * @param offsetX 阴影水平方向的偏移量
         * @param offsetY 阴影垂直方向的偏移量
         */
        fun setShadowDrawable(
            view: View,
            shapeRadius: Int,
            shadowColor: Int,
            shadowRadius: Int,
            offsetX: Int,
            offsetY: Int
        ) {
            val drawable = Builder()
                .setTarget(view)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }

        /**
         * 为指定View设置带阴影的背景
         * @param view 目标View
         * @param bgColor View背景色
         * @param shapeRadius View的圆角
         * @param shadowColor 阴影的颜色
         * @param shadowRadius 阴影的宽度
         * @param offsetX 阴影水平方向的偏移量
         * @param offsetY 阴影垂直方向的偏移量
         */
        fun setShadowDrawable(
            view: View,
            bgColor: Int,
            shapeRadius: Int,
            shadowColor: Int,
            shadowRadius: Int,
            offsetX: Int,
            offsetY: Int
        ) {
            val drawable = Builder()
                .setTarget(view)
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }

        /**
         * 为指定View设置指定形状并带阴影的背景
         * @param view 目标View
         * @param shape View的形状 取值可为：GradientDrawable.RECTANGLE， GradientDrawable.OVAL， GradientDrawable.RING
         * @param bgColor View背景色
         * @param shapeRadius View的圆角
         * @param shadowColor 阴影的颜色
         * @param shadowRadius 阴影的宽度
         * @param offsetX 阴影水平方向的偏移量
         * @param offsetY 阴影垂直方向的偏移量
         */
        fun setShadowDrawable(
            view: View,
            shape: Int,
            bgColor: Int,
            shapeRadius: Int,
            shadowColor: Int,
            shadowRadius: Int,
            offsetX: Int,
            offsetY: Int
        ) {
            val drawable = Builder()
                .setTarget(view)
                .setShape(shape)
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }

        /**
         * 为指定View设置带阴影的渐变背景
         * @param view
         * @param bgColor
         * @param shapeRadius
         * @param shadowColor
         * @param shadowRadius
         * @param offsetX
         * @param offsetY
         */
        fun setShadowDrawable(
            view: View,
            bgColor: IntArray,
            shapeRadius: Int,
            shadowColor: Int,
            shadowRadius: Int,
            offsetX: Int,
            offsetY: Int
        ) {
            val drawable = Builder()
                .setTarget(view)
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }
    }

    init {
        mBgColor = bgColor
        mBorderColor = borderColor
        mBorderWidth = borderWidth
        mShapeRadius = shapeRadius
        mShadowRadius = shadowRadius
        mOffsetX = offsetX
        mOffsetY = offsetY
        mShadowPaint = Paint()
        mShadowPaint.color = Color.TRANSPARENT
        mShadowPaint.isAntiAlias = true
        mShadowPaint.setShadowLayer(
            shadowRadius.toFloat(),
            offsetX.toFloat(),
            offsetY.toFloat(),
            shadowColor
        )
        mShadowPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
        mBgPaint = Paint()
        mBgPaint.isAntiAlias = true
        if (mBorderWidth > 0) {
            mBorderPaint = Paint()
            mBorderPaint?.let {
                it.isAntiAlias = true
                it.style = Paint.Style.STROKE
                it.color = mBorderColor
                it.strokeWidth = mBorderWidth.toFloat()
            }

        }

    }
}

fun Shadow(param: ShadowDrawable.Builder.() -> Unit) {
    val builder = ShadowDrawable.Builder()
    builder.param()
    builder.target?.let {
        it.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        ViewCompat.setBackground(it, builder.builder())
    }
}