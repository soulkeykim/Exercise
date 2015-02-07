package com.soulkey.androidexercise.Common;

import android.support.v7.app.ActionBar;
import android.util.Log;

import com.soulkey.androidexercise.Activity.ECActivity;
import com.soulkey.androidexercise.Client.ECDataHandler;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECGlobal {

    private static ECActivity currentActivity;
    private static String title = ECDefine.MSG_RECEIVING_DATA;

    public static void setCurrentActivity(ECActivity cActivity) {
        currentActivity = cActivity;

        ECDataHandler.init(currentActivity);
    }

    public static ECActivity getCurrentActivity()
    {
        return currentActivity;
    }

    public static void updateActionBarTitle()
    {
        ActionBar ab = currentActivity.getSupportActionBar();
        try {
            if (ab != null)
                ab.setTitle(getTitle());
        } catch (Exception e)
        {
            Log.e(ECDefine.TAG, "e = " + e.toString());
        }
    }

    public static void loadActionBarTitle()
    {
        setTitle(ECDefine.MSG_RECEIVING_DATA);
        updateActionBarTitle();
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        ECGlobal.title = title;
    }
}
