package com.imyeego.mozart

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.provider.Settings.EXTRA_APP_PACKAGE
import android.provider.Settings.EXTRA_CHANNEL_ID
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume


/**
 * @authur : liuzhao
 * @time   : 5/7/21 8:59 PM
 * @Des    :
 *
 */
class ThirdActivity : BaseActivity(){

    companion object {
        const val TAG = "ThirdActivity"
    }

    override fun initView(savedInstanceState: Bundle?) {
//        launch {
//            Log.e(TAG, Thread.currentThread().name)
//            val text = getText()
//            mytv.text = text
//
//            Glide.with(context).load("https://wx2.sinaimg.cn/mw690/006JOEnDly1gms2b4y01kj312z0u04qp.jpg").into(iv)
//        }
        mytv.text = "Shadow"

        mytv.setOnClickListener {
//            CommonDialog.Builder(context)
//                    .headerUrl("https://wx1.sinaimg.cn/mw690/8a45ddealy1gqjzn14qmmj22no1bwqv5.jpg")
//                    .title("通知")
//                    .subTitle("开启通知，方便接收消息")
//                    .cancelText("稍后开启")
//                    .doneText("现在开启")
//                    .onDone(object : CommonDialog.OnClickCallback {
//                        override fun onClick(v: View) {
//                            openAppNotification()
//                        }
//                    })
//                    .create()
//                    .show()


            Shadow {
                target = mytv
                mBgColor = intArrayOf(Color.parseColor("#F9F9F9"))
                mShadowRadius = 20
                mShapeRadius = 15
                mShadowColor = Color.parseColor("#AA212121")
                mOffsetX = 0
                mOffsetY = 15
                mBorderColor = "#FF5809"
                mBorderWidth = 5
            }
        }

        val json = ""
//        val person: Person = Gson().fromJson(json)

        val byteArray: ByteArray = Constant.BIN


    }

    private fun openAppNotification() {
        try {
            val intent = Intent()

            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            intent.putExtra(EXTRA_APP_PACKAGE, packageName)
            intent.putExtra(EXTRA_CHANNEL_ID, applicationInfo.uid)

            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用

            intent.putExtra("app_package", packageName)
            intent.putExtra("app_uid", applicationInfo.uid)

            // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，我想对雷布斯说：I'm not ok!!!
            //  if ("MI 6".equals(Build.MODEL)) {
            //      intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            //      Uri uri = Uri.fromParts("package", getPackageName(), null);
            //      intent.setData(uri);
            //      // intent.setAction("com.android.settings/.SubSettings");
            //  }
            startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent()

            //下面这种方案是直接跳转到当前应用的设置界面。
            //https://blog.csdn.net/ysy950803/article/details/71910806
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)

        }
    }

    private suspend fun getText() : String {
        delay(2000)
        return "liuzhao"
    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)

    override fun getLayoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
    }
}