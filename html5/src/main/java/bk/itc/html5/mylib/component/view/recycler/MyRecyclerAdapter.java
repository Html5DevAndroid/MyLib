package bk.itc.html5.mylib.component.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hien on 5/3/2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private MyRecyclerListener mListener;
    private int mSize = 0;

    public MyRecyclerAdapter(@NonNull MyRecyclerListener listener) {
        mListener = listener;
    }

    public void setSize(int size) {
        mSize = size;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mListener.onCreateView();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mListener.onBind(position, holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    public interface MyRecyclerListener {
        MyViewHolder onCreateView();
        void onBind(int position, View view);
    }
}
