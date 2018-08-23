package com.gbbeard.chatstatus;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyStatusViewHolder> {
    private List<UserModels> mStatuses;

    public UserListAdapter(List<UserModels> allStatuses) {
        mStatuses = allStatuses;
    }

    @Override
    public MyStatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item, parent, false);

        MyStatusViewHolder vh = new MyStatusViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyStatusViewHolder holder, int position) {
        UserModels status = mStatuses.get(position);
        holder.bind(status);
    }

    @Override
    public int getItemCount() {
        return mStatuses.size();
    }

    public void replaceList(List<UserModels> statuses) {
        mStatuses = statuses;
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ImageView icon;
        public TextView username;
        public TextView statusText;

        private UserModels mStatus;

        public MyStatusViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.statusIcon);
            this.username = mView.findViewById(R.id.username);
            this.statusText = mView.findViewById(R.id.statusText);
        }

        public void bind(UserModels status) {
            mStatus = status;

            this.username.setText(status.username);
            this.statusText.setText(status.statusText);

            setIcon();
        }

        public void setIcon() {
            // default to showing offline
            int imageId = R.drawable.red;
            if (mStatus.status.equals("online")) {
                imageId = R.drawable.green;
            } else if (mStatus.status.equals("away")) {
                imageId = R.drawable.yellow;
            } else if (mStatus.status.equals("offline")) {
                // explicitly program it so "offline" is associated
                // with red, even though that's also the default.
                imageId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }

}