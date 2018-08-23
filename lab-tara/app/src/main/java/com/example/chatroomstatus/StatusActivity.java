package com.example.chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chatroomstatus.models.Status;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusActivity extends AppCompatActivity {
    @BindView(R.id.userList) RecyclerView userList;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    List<Status> allStatuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ButterKnife.bind(this);

        allStatuses = new ArrayList<>();
        allStatuses.add(new Status("errandguy", "Away", "out running errands"));
        allStatuses.add(new Status("lurker", "Online", ""));
        allStatuses.add(new Status("talkyguy", "Online", "what's up!"));
        allStatuses.add(new Status("climberperson", "Offline", "mt baker 14,020 ft"));

        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatuses);

        userList.setLayoutManager(linearLayoutManager);
        userList.setAdapter(statusAdapter);
    }
}
