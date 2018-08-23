package com.amycohen.lab33chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    List<Status> allStatuses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ButterKnife.bind(this);

        allStatuses = new ArrayList<>();
        allStatuses.add(new Status("amy", "online", "should totally be working on homework"));
        allStatuses.add(new Status("gamer", "away", "busy learning fortnite dance"));

        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatuses);

        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(statusAdapter);
        
    }
}
