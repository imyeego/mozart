package com.imyeego.mozart

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

/**
 * @authur : liuzhao
 * @time   : 5/16/21 8:11 PM
 * @Des    :
 *
 */
class CommonDialog(context: Context) : Dialog(context) {

    var builder: Builder? = null

    override fun show() {
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setWindowAnimations(R.style.dialogWindowAnim)
        }
        super.show()
        builder?.asyncBuilder?.into(builder!!.ivHeader)

    }

    override fun dismiss() {
        super.dismiss()

    }

    private fun dp2px(context: Context, dp: Int) :Int {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5).toInt()
    }

    class Builder(context: Context) {
        private val mContext = context
        var ivHeader:ImageView
        private var tvTitle: TextView
        private var tvSubTitle: TextView
        private var dialog: CommonDialog = CommonDialog(context)
        private var root: View
        private var tvCancel: Button
        private var tvDone: Button

        private var cancelClickCallback: OnClickCallback? = null
        private var doneClickCallback: OnClickCallback? = null

        private var width: Int = 0
        var source: String? = ""
        private var roundCornerTransform: RoundCornerTransform
        var asyncBuilder: RequestBuilder<Bitmap>? = null

        init {
            val layoutInflater = LayoutInflater.from(mContext)
            root = layoutInflater.inflate(R.layout.dialog_common, null)


            ivHeader = root.findViewById(R.id.iv_header)
            tvTitle = root.findViewById(R.id.tv_title)
            tvSubTitle = root.findViewById(R.id.tv_subtitle)
            tvCancel = root.findViewById(R.id.tv_cancel)
            tvDone = root.findViewById(R.id.tv_done)


            var dm = DisplayMetrics()
            dialog.window?.windowManager?.defaultDisplay?.getMetrics(dm)
            width = (dm.widthPixels * 0.75f).toInt()

            roundCornerTransform = RoundCornerTransform(mContext, dialog.dp2px(mContext, 10).toFloat())
            roundCornerTransform.setCorner(false, false, true, true)

            dialog.builder = this
        }

        fun headerUrl(source: String?): Builder {
            this.source = source

            return this
        }

        fun title(title: String?): Builder {
            tvTitle.text = title
            return this
        }

        fun subTitle(title: String?): Builder {
            tvSubTitle.text = title
            return this
        }

        fun cancelText(text: String?): Builder {
            tvCancel.text = text
            return this
        }

        fun doneText(text: String?): Builder {
            tvDone.text = text
            return this
        }

        fun onCancel(cancelListener: OnClickCallback?): Builder {
            cancelClickCallback = cancelListener
            return this
        }



        fun onDone(doneListener: OnClickCallback?): Builder {
            doneClickCallback = doneListener
            return this
        }

        fun create(): CommonDialog {


            tvCancel.setOnClickListener {
                if (dialog.isShowing) {
                    cancelClickCallback?.onClick(it)
                    dialog.dismiss()
                }
            }

            tvDone.setOnClickListener {
                if (dialog.isShowing) {
                    doneClickCallback?.onClick(it)
                    dialog.dismiss()
                }
            }
            Log.e("width", "$width")
            dialog.addContentView(root, ViewGroup.LayoutParams(width
                    , ViewGroup.LayoutParams.WRAP_CONTENT))

            if (TextUtils.isEmpty(source))
                Glide.with(mContext).load(R.mipmap.dialog_header)
                        .transform(roundCornerTransform)
                        .into(ivHeader)
            else {
                asyncBuilder = Glide.with(mContext).asBitmap().load(source).transform(roundCornerTransform)
            }
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }

    }

    interface OnClickCallback {
        fun onClick(v: View):Unit
    }
}