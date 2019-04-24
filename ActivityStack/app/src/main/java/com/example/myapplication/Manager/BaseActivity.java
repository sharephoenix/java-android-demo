package com.example.myapplication.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity", getClass().getSimpleName()); //得当当前类的名称
        //--------------在“Acitivty最佳实践1”新增代码-----------------------------
        ActivityManager.instance().addActivity(this);
        //-------------------------------------------
    }

    //--------------在“Acitivty最佳实践1”新增代码-----------------------------
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityManager.instance().removeActivity(this);
    }
    //-----------------------------------------------------------------------

    public void actionStart(Context context, Class<?> className){
        Intent intent = new Intent(context, className);
        startActivity(intent);
    }

    public void presentStart(Context context, Class<?> className) {
        ActivityManager.instance().presentActivity();
        Intent intent = new Intent(context, className);
        startActivity(intent);
    }

}
