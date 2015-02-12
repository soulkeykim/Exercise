package com.soulkey.androidexercise.Task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.soulkey.androidexercise.Client.ECDataHandler;
import com.soulkey.androidexercise.Client.ECParser;
import com.soulkey.androidexercise.Common.ECDefine;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Event.UpdateEvent;
import com.soulkey.androidexercise.R;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

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
            RequestQueue queue = Volley.newRequestQueue(ECGlobal.getCurrentActivity(), new HurlStack());

            JsonRequest jsonRequest = getJSONRequest();
            queue.add(jsonRequest);

        } catch (Exception e) {

            return ECDefine.RESULT_FAILED;
        }

        return ECDefine.RESULT_SUCCESS;
    }

    protected void onProgressUpdate(Integer...a) {
    }

    protected void onPostExecute(String result) {
    }

    private JsonRequest getJSONRequest() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, ECDefine.getUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null)
                            ECParser.getInstance().Parsing(response.toString());

                        finishTask();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                ECGlobal.setTitle(ECDefine.MSG_CON_FAIL);
                showAlertView();

                finishTask();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                ECDefine.LENGTH_TIME_OUT,
                ECDefine.RETRY_COUNT,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return jsonRequest;
    }
    private void finishTask() {
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
