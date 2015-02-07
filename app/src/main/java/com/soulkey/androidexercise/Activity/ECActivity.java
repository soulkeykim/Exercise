package com.soulkey.androidexercise.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.soulkey.androidexercise.Common.ECGlobal;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ECGlobal.setCurrentActivity(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        ECGlobal.setCurrentActivity(this);
    }

}
