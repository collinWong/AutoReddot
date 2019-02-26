package com.colin.reddot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Reddot.ReddotLinsenter {

    TextView mMainDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainDot = findViewById(R.id.tv_main_dot);
        findViewById(R.id.tv_start).setOnClickListener(this);
        ReddotList.mainReddot.registerLisenter(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_start:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void updateView(String key, int count) {
        if (key.equals(ReddotList.mainReddot.getKey())) {
            mMainDot.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
            mMainDot.setText(count + "");
        }

    }
}
