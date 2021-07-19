package com.imyeego.mozart

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.layout_dialog.*

class BottomDialog(context: Context) : Dialog(context) {


    override fun show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_dialog)
        val win = window
        win!!.setWindowAnimations(R.style.dialogAnim)

        win.setGravity(Gravity.BOTTOM)
        val lp = win.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
        val click = action()
        tv_title.setOnClickListener {
            click()
        }

        super.show()
    }

    private fun action():() -> Unit {
        var visibility = false

        return fun() {
            edit_text.visibility = if (visibility) View.VISIBLE else View.GONE
            visibility = !visibility

        }
    }

}