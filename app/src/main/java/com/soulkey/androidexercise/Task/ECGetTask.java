package com.soulkey.androidexercise.Task;

import android.os.AsyncTask;

import com.soulkey.androidexercise.Client.ECClient;
import com.soulkey.androidexercise.Client.ECDataHandler;
import com.soulkey.androidexercise.Client.ECParser;
import com.soulkey.androidexercise.Common.ECDefine;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Event.UpdateEvent;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import de.greenrobot.event.EventBus;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
 public class ECGetTask extends AsyncTask<Void, Integer, String> {

    public ECGetTask()
    {
        ECGlobal.loadActionBarTitle();
        ECDataHandler.getInstance().clearRows();
    }

    protected void onPreExecute (){
    }

    protected String doInBackground(Void...arg0) {
        try {
            Request request = new Request.Builder()
                                         .url(ECDefine.getUrl())
                                         .build();

            Response response = ECClient.getInstance()
                                        .newCall(request)
                                        .execute();

            if(response != null && response.body() != null)
                ECParser.getInstance().Parsing(response.body().string());

        } catch (Exception e) {
        }

        return "PostExecute";
    }

    protected void onProgressUpdate(Integer...a){
    }

    protected void onPostExecute(String result) {
        EventBus.getDefault().post(new UpdateEvent());
    }
}
