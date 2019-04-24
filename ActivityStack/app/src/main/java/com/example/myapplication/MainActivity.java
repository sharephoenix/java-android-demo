package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Manager.ActivityManager;
import com.example.myapplication.Manager.BaseActivity;

public class MainActivity extends BaseActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.click);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityManager.stacks.size() > 3 && ActivityManager.instance().getNavigator().size() == 3) {
                    ActivityManager.instance().finish();
                }

                    if (ActivityManager.instance().getNavigator().size() < 3) {
                        actionStart(MainActivity.this, MainActivity.class);
                    } else {
                        presentStart(MainActivity.this, MainActivity.class);
                    }

            }
        });
    }
}
