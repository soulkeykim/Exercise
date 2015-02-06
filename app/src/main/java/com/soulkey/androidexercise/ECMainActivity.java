package com.soulkey.androidexercise;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.soulkey.androidexercise.Adapter.ECRecyclerAdapter;
import com.soulkey.androidexercise.Client.ECDataHandler;
import com.soulkey.androidexercise.Common.ECActivity;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Event.UpdateEvent;
import com.soulkey.androidexercise.Struct.ECRow;
import com.soulkey.androidexercise.Task.ECGetTask;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class ECMainActivity extends ECActivity {

    private ECRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        new ECGetTask().execute();
    }

    public void onEvent(UpdateEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ECGlobal.updateActionBarTitle();
            }
        });

        List<ECRow> listRow =  ECDataHandler.getInstance().getRows();
        for(ECRow row : listRow) {
            Log.e("###", "row = " + row.title);
            recyclerAdapter.add(row, 0);
        }
    }

    private void setRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new ECRecyclerAdapter(new ArrayList<ECRow>(), R.layout.item_row);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ECGlobal.getCurrentActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
