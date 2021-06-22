package com.imyeego.mozart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.imyeego.mozart.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Proxy;
import java.security.KeyPair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.crypto.Cipher;
import javax.inject.Inject;
import javax.inject.Named;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private TextView tv;
    private MyTextView myTv;

    @Inject
    Student student;
    @Inject
    @Named("1")
    Student student1;
    @Inject
    Person person;



    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Handler handler;

    MutableLiveData<String> liveData;
    ActivityMainBinding binding;

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Log.e(TAG, "onCreate");
//        DaggerMainComponent.create().injectFrom(this);
//        ((App)getApplication()).getAppComponent().inject(this);

        Person person = new Person("liuzhao", 18, "male");
        binding.setPerson(person);

        binding.mytv.setOnClickListener(v -> {
            binding.mytv.setText("shaqiansi");
            Log.e("databinding", person.getName());
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });

        String json = "{}";
        ShadowDrawable.Companion.setShadowDrawable(binding.mytv, 5
                , Color.parseColor("#2a000000"), 5, 0, 0);

        liveData = new MutableLiveData<>();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        getLifecycle().addObserver(new Run());
        Transformations.map(liveData, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input);
            }
        }).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });


    }

    class Run implements DefaultLifecycleObserver {

        @Override
        public void onPause(@NonNull LifecycleOwner owner) {
            Log.e("lifeCycle", "onPause");
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要退出")
                .create().show();
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
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }



    private void anrTest() {
        while (true) {

        }
    }

    private int count = 0;

    private Choreographer.FrameCallback fps = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
            count ++;
            Log.e("Choreographer", "FrameCallback :" + count);
        }
    };


    private static String getCommandResult(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            int read;
            char[] buffer = new char[128];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void execSuCmd(String cmd) {
        Process process = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            int aa = process.waitFor();
            is = new DataInputStream(process.getInputStream());
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            String out = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (process != null){
                    process.destroy();
                }

            } catch (Exception e) {
            }
        }
    }

    public void setSysDate(String date){
        try {
            Date c = format.parse(date);
            long when = c.getTime();
            ((AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE)).setTime(when);
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    private void rsaTest() {
        String data = "hello liuzhao";

        try {
            KeyPair keyPair = RSAUtil.generateRSAKeyPair(1024);

            byte[] publicKey = RSAUtil.getPublicKey(keyPair);
            Log.e("RSA","加密的公钥 " + Base64.encodeToString(publicKey, Base64.NO_PADDING));

            byte[] privateKey = RSAUtil.getPrivateKey(keyPair);

            byte[] encrypted = RSAUtil.encryptByPublicKey(data.getBytes(), publicKey);

            Log.e("RSA","加密后的数据 " + Base64.encodeToString(encrypted, Base64.NO_PADDING));

            byte[] originalBytes = RSAUtil.decryptByPrivateKey(encrypted, privateKey);
            Log.e("RSA", "解密后的数据 " + new String(originalBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}