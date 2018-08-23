package droid.yutani.com.chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import droid.yutani.com.chatroomstatus.model.Status;

public class StatusList extends AppCompatActivity {
    @BindView(R.id.recycler_list) RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    private List<Status> allStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);

        ButterKnife.bind(this);

        allStatus = new ArrayList<>();
        allStatus.add(new Status("Brando", "away", "Climbing rocks"));

        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatus);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(statusAdapter);
    }
}
