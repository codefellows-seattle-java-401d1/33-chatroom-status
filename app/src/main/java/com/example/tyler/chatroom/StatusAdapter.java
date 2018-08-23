package com.example.tyler.chatroom;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyStatusViewHolder> {
    private List<Status> mStatuses;

    public StatusAdapter(List<Status> allStatuses){
        mStatuses = allStatuses;
    }

    @Override
    public MyStatusViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_item, parent, false);

        MyStatusViewHolder vh = new MyStatusViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyStatusViewHolder holder, int position){
        Status status = mStatuses.get(position);
        holder.bind(status);
    }

    @Override
    public int getItemCount(){
        return mStatuses.size();
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ImageView icon;
        public TextView username;
        public TextView statusText;

        private Status mStatus;

        public MyStatusViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.statusIcon);
            this.username = mView.findViewById(R.id.username);
            this.statusText = mView.findViewById(R.id.statusText);
        }

        public void bind(Status status){
            mStatus = status;

            this.username.setText(status.username);
            this.statusText.setText(status.statusText);

            setIcon();
        }

        public void setIcon(){
            int imageId = R.drawable.offline;
            if(mStatus.status.equals("online")){
                imageId = R.drawable.online;
            } else if (mStatus.equals("away")){
                imageId = R.drawable.away;
            } else if (mStatus.equals("offline")){
                imageId = R.drawable.offline;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }
}
