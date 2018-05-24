package bk.itc.html5.mylib.component.view.topicview2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DrawableUtil;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerAdapter;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerView;
import bk.itc.html5.mylib.component.view.recycler.MyViewHolder;
import bk.itc.html5.mylib.component.view.topicview.TopicView;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hien on 5/23/2018.
 */

public class TopicView2 extends MyRecyclerView {
    private List<Item2> mItems = new ArrayList<>();
    private Drawable mBackground;
    private int mBackgroundColor;
    private int mTitleSize;
    private int mSnippetSize;
    private int mTitleColor;
    private int mSnippetColor;
    private String mActionText;

    public TopicView2(Context context) {
        super(context);
        init();
    }

    public TopicView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopicView2(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void configDefault() {
        mBackground = DrawableUtil.createBackground(Color.WHITE, 3, 0, Color.BLACK);
        mBackgroundColor = Color.WHITE;
        mTitleSize = 15;
        mTitleColor = Color.BLACK;
        mSnippetSize = 13;
        mSnippetColor = Color.BLACK;
        mActionText = getContext().getResources().getString(R.string.topic2_action_button);
    }

    public void configTitle(int color, int size) {
        mTitleColor = color;
        mTitleSize = size;

        setSize(mItems.size());
    }

    public void configSnippet(int color, int size) {
        mSnippetColor = color;
        mSnippetSize = size;

        setSize(mItems.size());
    }

    public void configBackground(Drawable drawable) {
        mBackground = drawable;

        setSize(mItems.size());
    }

    public void configBackgroundColor(int color) {
        mBackgroundColor = color;

        setSize(mItems.size());
    }

    public void configActionButton(String text) {
        mActionText = text;

        setSize(mItems.size());
    }

    private void init() {
        initUI();
        configDefault();
    }

    private void initUI() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new MyRecyclerAdapter.MyRecyclerListener() {
            @Override
            public MyViewHolder onCreateView() {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.topic_item_2, null, false);
                final MyViewHolder myViewHolder = new MyViewHolder(view);

                ViewGroup layout = (ViewGroup) view.findViewById(R.id.topic_item_2_layout);
                FancyButton button = (FancyButton) view.findViewById(R.id.topic_item_2_button);
                TextView title = (TextView) view.findViewById(R.id.topic_item_2_title);
                TextView snippet = (TextView) view.findViewById(R.id.topic_item_2_snippet);

                layout.setBackgroundColor(mBackgroundColor);
                title.setTextSize(mTitleSize);
                title.setTextColor(mTitleColor);
                snippet.setTextColor(mSnippetColor);
                snippet.setTextSize(mSnippetSize);
                button.setText(mActionText);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TopicListener2 listener = mItems.get(myViewHolder.getAdapterPosition()).listener;
                        if(listener != null) {
                            listener.onTopicClick(myViewHolder.getAdapterPosition());
                        }
                    }
                });

                return myViewHolder;
            }

            @Override
            public void onBind(int position, View view) {
                final ImageView image = (ImageView) view.findViewById(R.id.topic_item_2_image);
                TextView title = (TextView) view.findViewById(R.id.topic_item_2_title);
                TextView snippet = (TextView) view.findViewById(R.id.topic_item_2_snippet);

                Glide.with(getContext()).load(mItems.get(position).imageUrl).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        image.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(image);

                title.setText(mItems.get(position).title);
                snippet.setText(mItems.get(position).snippet);
            }
        });
    }

    public void addItem(String imgUrl, String title, String snippet, TopicListener2 listener) {
        mItems.add(new Item2(imgUrl, title, snippet, listener));
        setSize(mItems.size());
    }

    public class Item2 {
        String imageUrl;
        String title;
        String snippet;
        TopicListener2 listener;

        public Item2(String imageUrl, String title, String snippet, TopicListener2 listener) {
            this.imageUrl = imageUrl;
            this.title = title;
            this.snippet = snippet;
            this.listener = listener;
        }
    }

    public interface TopicListener2 {
        void onTopicClick(int position);
    }
}
