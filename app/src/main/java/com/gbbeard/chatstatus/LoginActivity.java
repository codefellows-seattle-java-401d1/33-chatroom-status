package com.gbbeard.chatstatus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.usernameInput)
    EditText mUsernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin)
    public void signin() {
        String username = mUsernameInput.getText().toString();

        Intent intent = new Intent(this, UserList.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
