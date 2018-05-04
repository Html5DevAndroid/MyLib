package bk.itc.html5.mylib.component.view.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Hien on 5/3/2018.
 */

public class MyRecyclerView extends RecyclerView {
    private MyRecyclerAdapter mAdapter;

    public MyRecyclerView(Context context) {
        super(context);
        init();
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    public void setAdapter(@NonNull MyRecyclerAdapter.MyRecyclerListener listener) {
        mAdapter = new MyRecyclerAdapter(listener);
        setAdapter(mAdapter);
    }

    public void setSize(int size) {
        mAdapter.setSize(size);
    }
}
