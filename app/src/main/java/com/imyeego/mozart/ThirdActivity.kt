package com.imyeego.mozart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

/**
 * @authur : liuzhao
 * @time   : 5/7/21 8:59 PM
 * @Des    :
 *
 */
class ThirdActivity : AppCompatActivity() {

    companion object {
        val TAG = "ThirdActivity"
    }

    val coroutineScope by lazy {
        CoroutineScope(Dispatchers.Main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            Log.e(TAG, Thread.currentThread().name)
            var text = getText()
            mytv.text = text
        }
    }

    private suspend fun getText() : String {
        var text = "";
        withContext(Dispatchers.IO) {
            Log.e(TAG, Thread.currentThread().name)
            delay(5000)
            text = "liuzhao"
        }


        return text;
    }
}