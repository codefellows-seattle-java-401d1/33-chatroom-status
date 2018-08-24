package com.example.chatroomstatus;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatroomstatus.models.Status;

import java.util.List;

class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyStatusViewHolder> {
    private List<Status> mStatuses;

    public StatusAdapter(List<Status> allStatuses) {
        mStatuses = allStatuses;
    }

    public void replaceList(List<Status> statuses) {
        mStatuses = statuses;
    }

    @NonNull
    @Override
    public MyStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_item, parent, false);

        MyStatusViewHolder vh = new MyStatusViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatusViewHolder holder, int position) {
        Status status = mStatuses.get(position);

        holder.bind(status);
    }

    @Override
    public int getItemCount() {
        return mStatuses.size();
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ImageView icon;
        public TextView username;
        public TextView statusText;

        private Status mStatus;

        public MyStatusViewHolder (View itemView) {
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.statusIcon);
            this.username = mView.findViewById(R.id.username);
            this.statusText = mView.findViewById(R.id.statusText);
        }

        public void bind(Status status) {
            mStatus = status;

            this.username.setText(status.username);
            this.statusText.setText(status.statusText);

            setIcon();
        }

        public void setIcon() {
            // Default status shows user is offline
            int imageId = R.drawable.red;

            if (mStatus.status.equals("Online")) {
                imageId = R.drawable.green;
            } else if (mStatus.status.equals("Away")) {
                imageId = R.drawable.yellow;
            } else if (mStatus.status.equals("Offline")) {
                // Explicity program so "offline" status is associated with red
                // Even though it's also the default
                imageId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }
}
