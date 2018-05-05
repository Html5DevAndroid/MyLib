package bk.itc.html5.mylib.component.view.gridnavigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerAdapter;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerView;
import bk.itc.html5.mylib.component.view.recycler.MyViewHolder;

/**
 * Created by Hien on 5/3/2018.
 */

public class GridNavigation extends MyRecyclerView {
    private List<Item> mItems = new ArrayList<>();
    private Drawable mDrawable;

    public GridNavigation(Context context) {
        super(context);
        init();
    }

    public GridNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GridNavigation(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(), 3));
        setAdapter(new MyRecyclerAdapter.MyRecyclerListener() {
            @Override
            public MyViewHolder onCreateView() {
                View view = View.inflate(getContext(), R.layout.grid_nav_item, null);
                final MyViewHolder myViewHolder = new MyViewHolder(view);

                if(mDrawable != null) {
                    view.findViewById(R.id.grid_item_layout).setBackground(mDrawable);
                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GridListener listener = mItems.get(myViewHolder.getAdapterPosition()).listener;
                        if(listener != null) {
                            listener.onClick();
                        }
                    }
                });

                return myViewHolder;
            }

            @Override
            public void onBind(int position, View view) {
                ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_icon);
                TextView textView = (TextView) view.findViewById(R.id.grid_item_text);

                Glide.with(getContext()).load(mItems.get(position).imageId).into(imageView);
                textView.setText(mItems.get(position).title);
            }
        });
    }

    public void setBackground(Drawable drawable) {
        mDrawable = drawable;
    }

    public void addItem(String title, int imageId, GridListener listener) {
        mItems.add(new Item(imageId, title, listener));
        setSize(mItems.size());
    }

    public class Item {
        int imageId;
        String title;
        GridListener listener;

        public Item(int id, String title, GridListener listener) {
            this.imageId = id;
            this.title = title;
            this.listener = listener;
        }
    }

    public interface GridListener {
        void onClick();
    }
}
