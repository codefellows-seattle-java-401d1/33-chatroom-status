package com.android.sooz.chatapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyStatusViewHolder> {

    private List<ChatStatus> mChatStatuses;

    //constructs all statuses as an object for use in the adapter and recycler view
    public StatusAdapter (List<ChatStatus> allStatuses){
        mChatStatuses = allStatuses;
    }

    @Override
    public StatusAdapter.MyStatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_item, parent, false);

    }
}
