package droid.yutani.com.chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import droid.yutani.com.chatroomstatus.model.Status;

public class StatusList extends AppCompatActivity {
    @BindView(R.id.recycler_list) RecyclerView mRecyclerView;
    @BindView(R.id.edit_status) EditText mEditText;
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    private List<Status> allStatus;

    FirebaseDatabase mDatabase;
    DatabaseReference mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);

        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance();
        mUsers = mDatabase.getReference("users");

        attachListener();

        allStatus = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        statusAdapter = new StatusAdapter(allStatus);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(statusAdapter);
    }

    public void attachListener() {
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Status> statuses = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String username = child.getKey();
                    String status = child.child("status").getValue(String.class);
                    String statusMsg = child.child("statusMsg").getValue(String.class);

                    Status userStatus = new Status(username, status, statusMsg);
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

    @OnClick(R.id.set_online)
    public void setOnline() {
        setStatus("online");
    }

    @OnClick(R.id.set_away)
    public void setAway() {
        setStatus("away");
    }

    @OnClick(R.id.set_offline)
    public void setOffline() {
        setStatus("offline");
    }

    public void setStatus(String status) {
        String username = "brando";
        String statusMsg = mEditText.getText().toString();

        DatabaseReference user = mUsers.child(username);
        user.setValue("status", status);
        user.setValue("statusMsg", statusMsg);
    }
}
