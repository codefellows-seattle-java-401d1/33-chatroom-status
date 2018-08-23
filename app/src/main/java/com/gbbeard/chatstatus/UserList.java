package com.gbbeard.chatstatus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserList extends AppCompatActivity {
    @BindView(R.id.setStatus)
    EditText mEditText;

    @BindView(R.id.list)
    RecyclerView list;
    private LinearLayoutManager linearLayoutManager;
    private UserListAdapter userListAdapter;

    private List<UserModels> allStatuses;

    FirebaseDatabase mDatabase;
    DatabaseReference mUsers;

    // keep track of the users username
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        ButterKnife.bind(this);


        mDatabase = FirebaseDatabase.getInstance();
        mUsers = mDatabase.getReference("users");

        attachListeners();
        initializeUsername();

        allStatuses = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        userListAdapter = new UserListAdapter(allStatuses);

        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(userListAdapter);
    }

    private void initializeUsername() {
        Intent data = getIntent();
        mUsername = data.getStringExtra("username");

        mUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(mUsername)) {
                    DatabaseReference userRef = mUsers.child(mUsername);
                    userRef.child("status").setValue("online");
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
                List<UserModels> statuses = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String username = child.getKey();
                    String status = child.child("status").getValue(String.class);
                    String statusText = child.child("statusText").getValue(String.class);

                    UserModels userStatus = new UserModels(username, status, statusText);
                    statuses.add(userStatus);
                }

                userListAdapter.replaceList(statuses);
                userListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.setOnline)
    public void setOnline() {
        setStatus("online");
    }

    @OnClick(R.id.setAway)
    public void setAway() {
        setStatus("away");
    }

    @OnClick(R.id.setOffline)
    public void setOffline() {
        setStatus("offline");
    }

    public void setStatus(String status) {
        String username = mUsername;
        String statusText = mEditText.getText().toString();

        DatabaseReference user = mUsers.child(username);
        user.child("status").setValue(status);
        user.child("statusText").setValue(statusText);
    }
}