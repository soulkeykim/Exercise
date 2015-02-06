package com.soulkey.androidexercise.Client;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECClient extends OkHttpClient {

    static private ECClient instance;

    static public ECClient getInstance()
    {
        if(instance == null)
            instance = new ECClient();

        return instance;
    }

}
