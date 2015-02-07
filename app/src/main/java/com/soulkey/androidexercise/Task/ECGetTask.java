package com.soulkey.androidexercise.Task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.soulkey.androidexercise.Client.ECClient;
import com.soulkey.androidexercise.Client.ECDataHandler;
import com.soulkey.androidexercise.Client.ECParser;
import com.soulkey.androidexercise.Common.ECDefine;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Event.UpdateEvent;
import com.soulkey.androidexercise.R;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import de.greenrobot.event.EventBus;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
 public class ECGetTask extends AsyncTask<Void, Integer, String> {

    public ECGetTask() {
        ECGlobal.loadActionBarTitle();

        //Clear previous data because of no identifier in JSON
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
            ECGlobal.setTitle(ECDefine.MSG_CON_FAIL);

            return ECDefine.RESULT_FAILED;
        }

        return ECDefine.RESULT_SUCCESS;
    }

    protected void onProgressUpdate(Integer...a) {
    }

    protected void onPostExecute(String result) {
        if(result.equals(ECDefine.RESULT_FAILED))
            showAlertView();

        EventBus.getDefault().post(new UpdateEvent());
    }

    private void showAlertView() {
        AlertDialog alertDialog = new AlertDialog.Builder(ECGlobal.getCurrentActivity()).create();

        alertDialog.setTitle(ECDefine.MSG_CON_FAIL);
        alertDialog.setMessage(ECDefine.MSG_TRY_AGAIN);
        alertDialog.setIcon(R.drawable.notification_error);

        alertDialog.setButton(ECDefine.MSG_OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }
}
