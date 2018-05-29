package bk.itc.html5.mylib.component.view.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bk.itc.html5.mylib.R;
import flepsik.github.com.progress_ring.ProgressRingView;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hien on 5/29/2018.
 */

public class ResultView extends RelativeLayout {
    private View mRoot;
    private FancyButton mViewAnswer;
    private FancyButton mRetest;
    private FancyButton mRateUs;
    private ProgressRingView mRing;
    private TextView mResultText;
    private TextView mAchievement;
    private FancyButton mBack;

    private Listener mListener;

    public ResultView(Context context) {
        super(context);
        init();
    }

    public ResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRoot = View.inflate(getContext(), R.layout.quiz_result_layout, null);
        mRoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mRoot);

        initUI();
        initListener();
    }

    private void initUI() {
        mViewAnswer = (FancyButton) mRoot.findViewById(R.id.quiz_result_view_answer);
        mRetest = (FancyButton)  mRoot.findViewById(R.id.quiz_result_retake);
        mRateUs = (FancyButton) mRoot.findViewById(R.id.quiz_result_rate);
        mRing = (ProgressRingView) mRoot.findViewById(R.id.quiz_result_ring);
        mResultText = (TextView) mRoot.findViewById(R.id.quiz_result_text);
        mAchievement = (TextView) mRoot.findViewById(R.id.quiz_result_achievement);
        mBack = (FancyButton) mRoot.findViewById(R.id.quiz_result_back);
    }

    private void initListener() {
        mViewAnswer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onViewResult();
                }
            }
        });

        mRetest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onRetake();
                }
            }
        });

        mRateUs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = getContext().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onBack();
                }
            }
        });
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setResultValue(String result) {
        mResultText.setText(result);
    }

    public void setRingColor(int color) {
        mRing.setProgressColor(color);
    }

    public void setRingPercent(float percent) {
        mRing.setProgress(percent);
    }

    public void setAchievement(String achievement) {
        mAchievement.setText(achievement);
    }

    public interface Listener {
        void onViewResult();
        void onRetake();
        void onBack();
    }
}
