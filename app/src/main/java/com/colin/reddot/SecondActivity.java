package com.colin.reddot;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by huangchuangliang on 2019/2/25.
 */
public class SecondActivity extends Activity implements View.OnClickListener, Reddot.ReddotLinsenter {

    TextView mLeftTv, mRigtTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mLeftTv = findViewById(R.id.tv_left_dot);
        mRigtTv = findViewById(R.id.tv_right_dot);
        findViewById(R.id.button_left_add).setOnClickListener(this);
        findViewById(R.id.button_left_reduce).setOnClickListener(this);
        findViewById(R.id.button_right_add).setOnClickListener(this);
        findViewById(R.id.button_right_reduce).setOnClickListener(this);
        ReddotList.secondReedot1.registerLisenter(this);
        ReddotList.secondReedot2.registerLisenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_left_add:
                ReddotList.secondReedot1.addOne();
                break;
            case R.id.button_left_reduce:
                ReddotList.secondReedot1.reduceOne();
                break;
            case R.id.button_right_add:
                ReddotList.secondReedot2.addOne();

                break;
            case R.id.button_right_reduce:
                ReddotList.secondReedot2.reduceOne();

                break;
        }
    }

    @Override
    public void updateView(String key, int count) {
        if (key.equals(ReddotList.secondReedot1.getKey())) {
            mLeftTv.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
            mLeftTv.setText(count + "");
        }

        if (key.equals(ReddotList.secondReedot2.getKey())) {
            mRigtTv.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
            mRigtTv.setText(count + "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReddotManager.clearLinsenter(ReddotList.secondReedot1.getKey());
        ReddotManager.clearLinsenter(ReddotList.secondReedot2.getKey());
    }
}
