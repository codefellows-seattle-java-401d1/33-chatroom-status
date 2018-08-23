package com.amycohen.lab33chatroomstatus;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        public TextView username;
        public TextView statusText;
        public ImageView icon;

        private Status mStatus;

        public MyStatusViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            username = mView.findViewById(R.id.username);
            statusText = mView.findViewById(R.id.statusText);
            icon = mView.findViewById(R.id.statusIcon);
        }

        public void bind(Status status) {
            mStatus = status;

            this.username.setText(status.username);
            this.statusText.setText(status.statusText);
            setIcon();
        }

        public void setIcon() {
            //offline as default
            int imageId = R.drawable.red;
            if (mStatus.status.equals("online")) {
                imageId = R.drawable.green;
            } else if (mStatus.status.equals("away")) {
                imageId = R.drawable.yellow;
            } else if (mStatus.status.equals("offline")) {
                imageId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }
}
