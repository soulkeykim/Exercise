package com.soulkey.androidexercise.Client;

import android.util.Log;

import com.soulkey.androidexercise.Common.ECDefine;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Common.ECMethod;
import com.soulkey.androidexercise.Struct.ECRow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECParser {
    static private ECParser instance;

    static public ECParser getInstance() {
        if (instance == null)
            instance = new ECParser();

        return instance;
    }

    public void Parsing(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            if(jsonObject.has(ECDefine.DEFINE_TITLE))
                ECGlobal.setTitle(jsonObject.getString(ECDefine.DEFINE_TITLE));

            if(jsonObject.has(ECDefine.DEFINE_ROWS)) {
                JSONArray rows = jsonObject.getJSONArray(ECDefine.DEFINE_ROWS);
                JSONParsing_ROWS(rows);
            }

        } catch (JSONException e) {
            Log.e(ECDefine.TAG, "e = " + e);
        }
    }

    private void JSONParsing_ROWS(JSONArray rows) {
        for (int i = 0; i < rows.length(); i++) {
            try {
                JSONObject rowJSON = rows.getJSONObject(i);

                //if title is null, skip the value.
                String title = checkString(rowJSON, ECDefine.TOKEN_TITLE);
                if(ECMethod.checkNull(title))
                    continue;

                //if description is null, change the value to empty
                String description = checkString(rowJSON,ECDefine.TOKEN_DESCRIPTION);
                if(ECMethod.checkNull(description))
                    description = "";

                ECRow ecRow = new ECRow();

                ecRow.title = title;
                ecRow.description = description;
                ecRow.imageHref = checkString(rowJSON, ECDefine.TOKEN_IMAGEHREF);

                ecRow.save();

            } catch (JSONException e) {

            }
        }
    }

    private String checkString(JSONObject rowJSON,String element)
    {
        try{
            if(rowJSON.has(element))
                return rowJSON.getString(element);

        } catch (JSONException e) {
            Log.e(ECDefine.TAG, " e = " + e);
        }

        return "";
    }
}
