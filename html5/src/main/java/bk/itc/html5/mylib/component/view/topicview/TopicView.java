package bk.itc.html5.mylib.component.view.topicview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DimenUtil;
import bk.itc.html5.mylib.component.util.DrawableUtil;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerAdapter;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerView;
import bk.itc.html5.mylib.component.view.recycler.MyViewHolder;

/**
 * Created by Hien on 5/4/2018.
 */

public class TopicView extends MyRecyclerView {
    private List<Item> mItems = new ArrayList<>();
    private int mTextColor = Color.BLACK;
    private int mBackgroundColor = Color.WHITE;
    private int mBackgroundRadius = 3;
    private int mStroke = 0;
    private int mStrokeColor = Color.WHITE;
    private int mImageSize = 60;
    private int mTextSize = 18;
    private int mTextRule = -100;

    public static int TEXT_LEFT = 1;
    public static int TEXT_CENTER = 0;
    public static int TEXT_RIGHT = -1;

    public TopicView(Context context) {
        super(context);
        init();
    }

    public TopicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopicView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new MyRecyclerAdapter.MyRecyclerListener() {
            @Override
            public MyViewHolder onCreateView() {
                View view = View.inflate(getContext(), R.layout.topic_item, null);
                final MyViewHolder myViewHolder = new MyViewHolder(view);

                ImageView icon = (ImageView) view.findViewById(R.id.topic_item_icon);
                ViewGroup.LayoutParams params = icon.getLayoutParams();
                int pxSize = (int)DimenUtil.pxFromDp(mImageSize);
                params.width = pxSize;
                params.height = pxSize;

                TextView textView = (TextView) view.findViewById(R.id.topic_item_title);
                ViewGroup layout = (ViewGroup) view.findViewById(R.id.topic_item_layout);

                if(mTextRule != -100) {
                    RelativeLayout.LayoutParams textParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                    textParams.removeRule(RelativeLayout.END_OF);
                    textParams.removeRule(RelativeLayout.CENTER_HORIZONTAL);
                    textParams.removeRule(RelativeLayout.ALIGN_PARENT_END);

                    if(mTextRule == RelativeLayout.END_OF) {
                        textParams.addRule(mTextRule, R.id.topic_item_icon);
                    }else {
                        textParams.addRule(mTextRule, RelativeLayout.TRUE);
                    }
                }

                textView.setTextColor(mTextColor);
                textView.setTextSize(mTextSize);
                layout.setBackground(DrawableUtil.createBackground(mBackgroundColor, mBackgroundRadius, mStroke, mStrokeColor));

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TopicListener listener = mItems.get(myViewHolder.getAdapterPosition()).listener;
                        if(listener != null) {
                            listener.onTopicClick();
                        }
                    }
                });

                return myViewHolder;
            }

            @Override
            public void onBind(int position, View view) {
                ImageView imageView = (ImageView) view.findViewById(R.id.topic_item_icon);
                TextView textView = (TextView) view.findViewById(R.id.topic_item_title);
                ViewGroup layout = (ViewGroup) view.findViewById(R.id.topic_item_layout);

                Glide.with(getContext()).load(mItems.get(position).iconId).into(imageView);
                textView.setText(mItems.get(position).title);
            }
        });
    }

    public void config(int  backgroundColor, int backgroundRadius) {
        mBackgroundColor = backgroundColor;
        mBackgroundRadius = backgroundRadius;

        setSize(mItems.size());
    }

    public void configStroke(int stroke, int strokeColor) {
        mStroke = stroke;
        mStrokeColor = strokeColor;

        setSize(mItems.size());
    }

    public void configTextAlign(int align) {
        if(align == TEXT_LEFT) {
            mTextRule = RelativeLayout.END_OF;
        }

        if(align == TEXT_CENTER) {
            mTextRule = RelativeLayout.CENTER_HORIZONTAL;
        }

        if(align == TEXT_RIGHT) {
            mTextRule = RelativeLayout.ALIGN_PARENT_END;
        }

        setSize(mItems.size());
    }

    public void configText(int textColor, int textSize) {
        mTextColor = textColor;
        mTextSize = textSize;

        setSize(mItems.size());
    }

    public void configImageSize(int size) {
        mImageSize = size;

        setSize(mItems.size());
    }

    public void addItem(String title, int imageId, TopicListener listener) {
        mItems.add(new Item(imageId, title, listener));
        setSize(mItems.size());
    }

    public class Item {
        int iconId;
        String title;
        TopicListener listener;

        public Item(int id, String title, TopicListener listener) {
            this.iconId = id;
            this.title = title;
            this.listener = listener;
        }
    }

    public interface TopicListener {
        void onTopicClick();
    }
}
