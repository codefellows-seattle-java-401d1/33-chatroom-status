package com.example.chatroomstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chatroomstatus.models.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusActivity extends AppCompatActivity {
    @BindView(R.id.setStatus) EditText mEditText;

    @BindView(R.id.userList) RecyclerView userList;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    private List<Status> allStatuses;

    FirebaseDatabase mDatabase;
    DatabaseReference mUsers;

    // Keep track of the users username
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance();
        mUsers = mDatabase.getReference("users");

        attachListeners();
        initializeUsername();

        allStatuses = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatuses);

        userList.setLayoutManager(linearLayoutManager);
        userList.setAdapter(statusAdapter);
    }

    private void initializeUsername() {
        Intent data = getIntent();
        mUsername = data.getStringExtra("username");

        mUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(mUsername)) {
                    DatabaseReference userRef = mUsers.child(mUsername);
                    userRef.child("status").setValue("Online");
                    userRef.child("statusText").setValue("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void attachListeners() {
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Status> statuses = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String username = child.getKey();
                    String status = child.child("status").getValue(String.class);
                    String statusText = child.child("statusText").getValue(String.class);

                    Status userStatus = new Status(username, status, statusText);
                    statuses.add(userStatus);
                }

                statusAdapter.replaceList(statuses);
                statusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.setOnline)
    public void setOnline() {
        setStatus("Online");
    }

    @OnClick(R.id.setAway)
    public void setAway() {
        setStatus("Away");
    }

    @OnClick(R.id.setOffline)
    public void setOffline() {
        setStatus("Offline");
    }

    public void setStatus(String status) {
        String username = mUsername;
        String statusText = mEditText.getText().toString();

        DatabaseReference user = mUsers.child(username);
        user.child("status").setValue(status);
        user.child("statusText").setValue(statusText);
    }
}
