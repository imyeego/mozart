package com.imyeego.mozart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        Log.e(TAG, "coroutineScope init ...")
        CoroutineScope(Dispatchers.Main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            Log.e(TAG, Thread.currentThread().name)

        }
    }
}