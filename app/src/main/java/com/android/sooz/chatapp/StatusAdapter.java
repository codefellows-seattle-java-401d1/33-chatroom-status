package com.android.sooz.chatapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        MyStatusViewHolder viewHolder = new MyStatusViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyStatusViewHolder holder, int position) {
        ChatStatus chatStatus = mChatStatuses.get(position);
        holder.bind(chatStatus);
    }

    @Override
    public int getItemCount() {
        return mChatStatuses.size();
    }

    public void replaceList(List<ChatStatus> chatStatuses){
        mChatStatuses = chatStatuses;
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ImageView icon;
        public TextView username;
        public TextView statusText;

        private ChatStatus mChatStatus;

        public MyStatusViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.statusIcon);
            this.username = mView.findViewById(R.id.username);
            this.statusText = mView.findViewById(R.id.statusText);
        }

        public void bind(ChatStatus chatStatus) {
            mChatStatus = chatStatus;

            this.username.setText(chatStatus.username);
            this.statusText.setText(chatStatus.statusText);

            setIcon();
        }

        public void setIcon(){
            //default to showing offline

            int imageId = R.drawable.red;

            if(mChatStatus.status.equals("online")){
                imageId = R.drawable.green;
            } else if(mChatStatus.status.equals("away")){
                imageId = R.drawable.yellow;
            } else if(mChatStatus.status.equals("offline")){
                imageId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }
}
