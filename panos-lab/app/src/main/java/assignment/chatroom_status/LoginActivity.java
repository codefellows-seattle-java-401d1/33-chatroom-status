package assignment.chatroom_status;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import assignment.chatroom_status.Classes.ChatroomStatus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.usernameInput)
    EditText mUsernameInput;
    @BindView(R.id.loginButton)
    Button mloginButton;
    @BindView(R.id.statusInput)
    EditText mStatusInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final Intent i = new Intent(this, UserList.class);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameInput.getText().toString();
                String status = mStatusInput.getText().toString();
                ChatroomStatus submit = new ChatroomStatus(username,status,"online");
                DatabaseReference myRef = database.getReference("user/");
                myRef.child(username).setValue(submit);
                startActivity(i);
            }
        });
    }

}
