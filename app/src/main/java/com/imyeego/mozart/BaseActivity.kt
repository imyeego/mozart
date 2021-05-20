package com.imyeego.mozart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * @authur : liuzhao
 * @time   : 5/9/21 3:02 PM
 * @Des    :
 *
 */
abstract class BaseActivity : AppCompatActivity() , CoroutineScope by MainScope(){

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        context = this
        initView(savedInstanceState)
        initData()
    }

    open fun initView(savedInstanceState: Bundle?) {}

    open fun initData(){}

    abstract fun getLayoutId(): Int

    internal inline fun <reified T: Activity> startActivity(context: Context) {
        startActivity(Intent(context, T::class.java))
    }
}