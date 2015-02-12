package com.soulkey.androidexercise.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.soulkey.androidexercise.Adapter.ECRecyclerAdapter;
import com.soulkey.androidexercise.Client.ECDataHandler;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Event.UpdateEvent;
import com.soulkey.androidexercise.R;
import com.soulkey.androidexercise.Struct.ECRow;
import com.soulkey.androidexercise.Task.ECGetTask;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class ECMainActivity extends ECActivity {

    private ECRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    private ImageView refreshView;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        setRefreshView();
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setRefreshMenu(menu);
        return true;
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new ECRecyclerAdapter(new ArrayList<ECRow>(), R.layout.item_row);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ECGlobal.getCurrentActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setRefreshMenu(Menu menu) {
        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setActionView(refreshView);
    }

    private void setRefreshView() {
        LayoutInflater inflater = (LayoutInflater) ECGlobal.getCurrentActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        refreshView = (ImageView) inflater.inflate(R.layout.action_refresh, null);

        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        if(isLoading)
            return;

        startLoading();

        recyclerAdapter.updateList(new ArrayList<ECRow>());
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
            recyclerAdapter.add(row, 0);
        }

        stopLoading();
    }

    private void startLoading()
    {
        isLoading = true;

        Animation rotation = AnimationUtils.loadAnimation(ECGlobal.getCurrentActivity(), R.anim.rotate);
        if(refreshView != null)
            refreshView.startAnimation(rotation);
    }

    private void stopLoading()
    {
        if(refreshView != null)
            refreshView.clearAnimation();

        isLoading = false;
    }

}
