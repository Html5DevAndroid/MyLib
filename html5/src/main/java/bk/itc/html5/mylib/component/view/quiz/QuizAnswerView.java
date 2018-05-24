package bk.itc.html5.mylib.component.view.quiz;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DimenUtil;
import bk.itc.html5.mylib.component.util.DrawableUtil;

/**
 * Created by Hien on 5/19/2018.
 */

public class QuizAnswerView extends RelativeLayout {
    private FrameLayout mMarkView;
    private TextView mAnswerTextView;
    private CheckBox mCheckBox;
    private RadioButton mRadioButton;

    public QuizAnswerView(Context context) {
        super(context);
        init();
    }

    public QuizAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuizAnswerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initUI();
        configDefault();
    }

    private void initUI() {
        mMarkView = new FrameLayout(getContext());
        mMarkView.setId(View.generateViewId());
        RelativeLayout.LayoutParams answerMarkParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        answerMarkParam.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        mMarkView.setLayoutParams(answerMarkParam);
        addView(mMarkView);

        mCheckBox = new CheckBox(getContext());
        mRadioButton = new RadioButton(getContext());

        mMarkView.addView(mCheckBox);
        mMarkView.addView(mRadioButton);

        mAnswerTextView = new TextView(getContext());
        RelativeLayout.LayoutParams answerTextParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        answerTextParam.addRule(RelativeLayout.END_OF, mMarkView.getId());
        answerTextParam.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        mAnswerTextView.setLayoutParams(answerTextParam);
        addView(mAnswerTextView);

        int padding = (int) DimenUtil.pxFromDp(5);
        setPadding(padding, padding, padding, padding);
    }

    private void configDefault() {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) mAnswerTextView.getLayoutParams();
        marginLayoutParams.leftMargin = (int) DimenUtil.pxFromDp(15);
        mAnswerTextView.setTextSize(16);
        mAnswerTextView.setTextColor(getContext().getResources().getColor(android.R.color.black));

        mCheckBox.setClickable(false);
        mRadioButton.setClickable(false);

        setType(true);
    }

    public void setType(boolean isSingleChoice) {
        if(isSingleChoice) {
            mCheckBox.setVisibility(GONE);
            mRadioButton.setVisibility(VISIBLE);
        }else {
            mCheckBox.setVisibility(VISIBLE);
            mRadioButton.setVisibility(GONE);
        }
    }

    public TextView getAnswerTextView() {
        return mAnswerTextView;
    }

    public void setAnswer(String answer) {
        mAnswerTextView.setText(answer);
    }

    public void setCheck(boolean isCheck) {
        mCheckBox.setChecked(isCheck);
        mRadioButton.setChecked(isCheck);
    }

    @Override
    public void setBackground(Drawable background) {
        if(background == getBackground())
            return;

        super.setBackground(background);
    }
}
