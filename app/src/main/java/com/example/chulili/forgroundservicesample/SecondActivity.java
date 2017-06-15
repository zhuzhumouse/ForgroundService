package com.example.chulili.forgroundservicesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by chulili on 2017/5/12.
 */

public class SecondActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        startService(new Intent(SecondActivity.this, SampleBackgroundSecondService.class));
        startService(new Intent(SecondActivity.this, SampleBackgroundServiceafjaispdfjasdeljflkd.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
