package com.soulkey.androidexercise.Client;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.soulkey.androidexercise.Struct.ECRow;

import java.util.List;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
public class ECDataHandler {

    private static ECDataHandler instance = null;

    public static ECDataHandler getInstance() {
        if (instance == null)
            throw new RuntimeException("This singleton must be initialised before anything else");
        return instance;
    }

    public static void init(Context c) {
        if (instance == null) {
            instance = new ECDataHandler(c);
        }
    }

    public ECDataHandler(Context c) {
        Configuration.Builder configurationBuilder = new Configuration.Builder(c);
        configurationBuilder.addModelClass(ECRow.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }

    public List<ECRow> getRows() {
        return new Select()
                .from(ECRow.class)
                .orderBy("id DESC")
                .execute();
    }

    public void clearRows()
    {
        new Delete()
                .from(ECRow.class)
                .execute();
    }

}