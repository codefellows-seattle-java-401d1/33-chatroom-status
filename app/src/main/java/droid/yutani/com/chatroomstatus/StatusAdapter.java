package droid.yutani.com.chatroomstatus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private Status mStatus;

        public MyStatusViewHolder (View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void bind(Status status) {
            mStatus = status;
        }
    }
}
