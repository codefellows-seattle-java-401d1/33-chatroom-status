package com.amycohen.lab33chatroomstatus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyStatusViewHolder> {
    private List<Status> mStatuses;

    public StatusAdapter(List<Status> allStatuses) {
        mStatuses = allStatuses;
    }

    @Override
    public MyStatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_item, parent, false);
        MyStatusViewHolder vh = new MyStatusViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyStatusViewHolder holder, int position) {
        Status status = mStatuses.get(position);
        holder.bind(status);
    }

    @Override
    public int getItemCount() {
        return mStatuses.size();
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder{

        View mView;
        private Status mStatus;

        public MyStatusViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void bind(Status status) {
            mStatus = status;
        }
    }
}
