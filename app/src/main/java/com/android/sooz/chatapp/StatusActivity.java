package com.android.sooz.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusActivity extends AppCompatActivity {

    @BindView(R.id.setStatus)
    public EditText mEditText;

    @BindView(R.id.list)
    public RecyclerView list;

    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ButterKnife.bind(this);

    }
}
