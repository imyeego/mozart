package com.imyeego.mozart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

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
        launch {
            Log.e(TAG, Thread.currentThread().name)
            var text = getText()
            mytv.text = text
            startActivity<SecondActivity>(context)
        }

        val json = ""
        val person: Person = Gson().fromJson(json)


    }

    private suspend fun getText() : String {
        var text = "";
        withContext(Dispatchers.IO) {
            Log.e(TAG, Thread.currentThread().name)
            delay(5000)
            text = "liuzhao"
        }


        return text
    }



    inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)

    override fun getLayoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
    }
}