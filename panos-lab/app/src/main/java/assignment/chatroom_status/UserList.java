package assignment.chatroom_status;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import assignment.chatroom_status.Classes.ChatroomStatus;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserList  extends AppCompatActivity{
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = mDatabase.getReference("user/");
    List<ChatroomStatus> mStatuses;
    private ChatroomStatusAdapter mChatroomStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        mStatuses = new ArrayList<>();

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mChatroomStatusAdapter = new ChatroomStatusAdapter(mStatuses);

        mRecyclerView.setAdapter(mChatroomStatusAdapter);

        attachUsersListener();
    }

    public void attachUsersListener(){
        DatabaseReference usersRef = mDatabase.getReference("user");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ChatroomStatus> statuses = new ArrayList<>();
                for(DataSnapshot user : dataSnapshot.getChildren()){
                    String username = user.getKey();

                    String status = user.child("status").getValue(String.class);

                    String statusText = "";
                    if(user.hasChild("statusText")){
                        statusText = user.child("statusText").getValue(String.class);
                    }

                    ChatroomStatus chatStatus = new ChatroomStatus(username,status,statusText);
                    statuses.add(chatStatus);
                }

                mChatroomStatusAdapter.setStatuses(statuses);
                mChatroomStatusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
