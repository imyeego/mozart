package com.imyeego.mozart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @authur : liuzhao
 * @time : 3/19/21 11:25 AM
 * @Des :
 */
public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        handler.postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));

        }, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
