package com.android.sooz.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.usernameInput)
    public EditText mUsernameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void login(){
        String username = mUsernameInput.getText().toString();

        //note to self, try refactoring intents with SharedPreferences later
        Intent intent = new Intent(this,StatusActivity.class);
    }
}
