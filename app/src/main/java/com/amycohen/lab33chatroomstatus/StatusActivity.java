package com.amycohen.lab33chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusActivity extends AppCompatActivity {

    public static final String TAG = "FIREBASE: ";
    @BindView(R.id.list)
    RecyclerView list;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    List<Status> allStatuses;

    FirebaseDatabase mDatabase;
    DatabaseReference mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ButterKnife.bind(this);


        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance();
        mUsers = mDatabase.getReference("users");

        attachListeners();

        // Read from the database
//        mUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

        allStatuses = new ArrayList<>();
        allStatuses.add(new Status("amy", "online", "should totally be working on homework"));
        allStatuses.add(new Status("gamer", "away", "busy learning fortnite dance"));

        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatuses);

        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(statusAdapter);

    }

    public void attachListeners() {
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Status> statuses = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()){
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
}
