package com.imyeego.mozart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * @authur : liuzhao
 * @time : 1/27/21 1:59 PM
 * @Des :
 */
public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    @Inject
    Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e(TAG, "onCreate");

//        ((App)getApplication()).getAppComponent().inject(this);
//        Log.i("SecondActivity", "" + person);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MyService.class));

        Log.e(TAG, "onDestroy");

    }
}
