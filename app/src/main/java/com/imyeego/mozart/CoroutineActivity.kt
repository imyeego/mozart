package com.imyeego.mozart

import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : BaseActivity() {

    companion object {
        private const val TAG = "CoroutineActivity"
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        launch { 
            val id = getUserId()
//            Log.e(TAG, name)
        }
    }
    
    private suspend fun getUserId(): Int {
        delay(2000)
        return 100
    }

    private suspend fun getName(id: Int): String {
        delay(3000)
        return "liuzhao"
    }
}