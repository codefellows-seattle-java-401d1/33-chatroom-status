package droid.yutani.com.chatroomstatus;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import droid.yutani.com.chatroomstatus.model.Status;

class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyStatusViewHolder> {
    private List<Status> mAllStatus;

    public StatusAdapter(List<Status> allStatus) {
        mAllStatus = allStatus;
    }

    @Override
    public MyStatusViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.status_item, viewGroup, false);

        MyStatusViewHolder vh = new MyStatusViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatusViewHolder myStatusViewHolder, int i) {
        Status status = mAllStatus.get(i);
        myStatusViewHolder.bind(status);
    }

    @Override
    public int getItemCount() {
        return mAllStatus.size();
    }

    public void replaceList(List<Status> statuses) {
        mAllStatus = statuses;
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private Status mStatus;

        public ImageView icon;
        public TextView username;
        public TextView statusMsg;

        public MyStatusViewHolder (View itemView) {
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.status_icon);
            this.username = mView.findViewById(R.id.username);
            this. statusMsg = mView.findViewById(R.id.statusMsg);
        }

        public void bind(Status status) {
            mStatus = status;

            this.username.setText(status.username);
            this.statusMsg.setText(status.statusMsg);
            setIcon();
        }

        public void setIcon() {
            int imgId = R.drawable.red;

            if (mStatus.status.equals("online")) {
                imgId = R.drawable.green;
            } else if (mStatus.status.equals("away")) {
                imgId = R.drawable.yellow;
            } else if (mStatus.status.equals("offline")) {
                imgId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imgId);
            icon.setImageDrawable(drawable);
        }
    }
}
