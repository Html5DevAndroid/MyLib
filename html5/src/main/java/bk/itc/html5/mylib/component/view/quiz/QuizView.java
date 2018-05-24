package bk.itc.html5.mylib.component.view.quiz;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DimenUtil;
import bk.itc.html5.mylib.component.util.DrawableUtil;

/**
 * Created by Hien on 5/19/2018.
 */

public class QuizView extends RelativeLayout {
    private TextView mQuestionStt;
    private TextView mQuestionContent;
    private List<QuizAnswerView> mAnswerViews = new ArrayList<>();
    private LinearLayout mLinerLayout;
    private LinearLayout mQuestionLayout;
    private LinearLayout mAnswerLayout;
    private ViewGroup mBottomLayout;
    private ScrollView mScrollView;
    private QuizAdapter mQuizAdapter;

    private TextView prevBtn;
    private TextView nextBtn;
    private TextView doneBtn;

    private Drawable mTrueDrawable;
    private Drawable mFalseDrawable;
    private Drawable mNormalDrawable;

    private boolean mIsShowResult;

    private int mMode = MODE_SINGLE_CHOICE;

    public static final int MODE_SINGLE_CHOICE = 1;
    public static final int MODE_MULTIPLE_CHOICE = 2;

    public QuizView(Context context) {
        super(context);
        init();
    }

    public QuizView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuizView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initUI();
        initBottomLayout();
        configDefault();

        mQuizAdapter = new QuizAdapter();
    }

    private void initUI() {
        mBottomLayout = new LinearLayout(getContext());
        mBottomLayout.setId(View.generateViewId());
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mBottomLayout.setLayoutParams(bottomParams);
        addView(mBottomLayout);

        mScrollView = new ScrollView(getContext());
        RelativeLayout.LayoutParams scrollParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollParams.addRule(RelativeLayout.ABOVE, mBottomLayout.getId());
        mScrollView.setLayoutParams(scrollParams);
        addView(mScrollView);

        mLinerLayout = new LinearLayout(getContext());
        mLinerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLinerLayout.setOrientation(LinearLayout.VERTICAL);
        mScrollView.addView(mLinerLayout);

        mQuestionLayout = new LinearLayout(getContext());
        mQuestionLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mQuestionLayout.setOrientation(LinearLayout.VERTICAL);
        mLinerLayout.addView(mQuestionLayout);

        mAnswerLayout = new LinearLayout(getContext());
        mAnswerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mAnswerLayout.setOrientation(LinearLayout.VERTICAL);
        mLinerLayout.addView(mAnswerLayout);

        mQuestionStt = new TextView(getContext());
        mQuestionStt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mQuestionLayout.addView(mQuestionStt);

        mQuestionContent = new TextView((getContext()));
        mQuestionContent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mQuestionLayout.addView(mQuestionContent);
    }

    private void initBottomLayout() {
        View view = View.inflate(getContext(), R.layout.quiz_bottom_layout, null);
        mBottomLayout.addView(view);

        prevBtn = (TextView) view.findViewById(R.id.quiz_bottom_previous);
        nextBtn = (TextView) view.findViewById(R.id.quiz_bottom_next);
        doneBtn = (TextView) view.findViewById(R.id.quiz_bottom_done);

        prevBtn.setBackground(DrawableUtil.createBackground(Color.WHITE, 2, 1, Color.BLACK));
        nextBtn.setBackground(DrawableUtil.createBackground(Color.WHITE, 2, 1, Color.BLACK));
        doneBtn.setBackground(DrawableUtil.createBackground(Color.WHITE, 2, 1, Color.BLACK));

        prevBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuizAdapter.goPrevious();
                goTo(mQuizAdapter.getCurrentQuestion());
            }
        });

        nextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuizAdapter.goNext();
                goTo(mQuizAdapter.getCurrentQuestion());
            }
        });

        doneBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public TextView getPrevBtn() {
        return prevBtn;
    }

    public TextView getNextBtn() {
        return nextBtn;
    }

    public TextView getDoneBtn() {
        return doneBtn;
    }

    private void configDefault() {
        mQuestionStt.setTextSize(15);
        mQuestionStt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        mQuestionStt.setTextColor(getContext().getResources().getColor(android.R.color.black));

        mQuestionContent.setTextSize(15);
        mQuestionContent.setTextColor(getContext().getResources().getColor(android.R.color.black));
        MarginLayoutParams marginContent = (MarginLayoutParams) mQuestionContent.getLayoutParams();
        marginContent.topMargin = (int) DimenUtil.pxFromDp(5);

        MarginLayoutParams marginAnswerLayout = (MarginLayoutParams) mAnswerLayout.getLayoutParams();
        marginAnswerLayout.topMargin = (int) DimenUtil.pxFromDp(30);

        setNormalDrawable(DrawableUtil.createBackground(Color.WHITE, 2, 2, Color.BLACK));
        setTrueBackground(DrawableUtil.createBackground(getContext().getResources().getColor(R.color.quiz_answer_true_bg), 2, 2, Color.BLACK));
        setFalseBackground(DrawableUtil.createBackground(getContext().getResources().getColor(R.color.quiz_answer_false_bg), 2, 2, Color.BLACK));

    }

    public void setTrueBackground(Drawable drawable) {
        mTrueDrawable = drawable;
    }

    public void setFalseBackground(Drawable drawable) {
        mFalseDrawable = drawable;
    }

    public void setNormalDrawable(Drawable drawable) {
        mNormalDrawable = drawable;
        resetAnswerBackground();
    }

    public void setStt(String stt) {
        mQuestionStt.setText(stt);
    }

    public void setQuestion(String question) {
        mQuestionContent.setText(question);
    }

    public TextView getQuestionStt() {
        return mQuestionStt;
    }

    public TextView getQuestionContent() {
        return mQuestionContent;
    }

    public ViewGroup getQuestionLayout() {
        return mQuestionLayout;
    }

    public ViewGroup getAnswerLayout() {
        return mAnswerLayout;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public void updateMode() {
        for (int i=0; i<mAnswerViews.size(); i++) {
            if(mMode == MODE_SINGLE_CHOICE) {
                mAnswerViews.get(i).setType(true);
            }
            if(mMode == MODE_MULTIPLE_CHOICE) {
                mAnswerViews.get(i).setType(false);
            }
        }
    }

    public void addAnswer(String answer) {
        final QuizAnswerView quizAnswerView = new QuizAnswerView(getContext());
        quizAnswerView.setAnswer(answer);
        mAnswerLayout.addView(quizAnswerView);
        mAnswerViews.add(quizAnswerView);

        View space = new View(getContext());
        space.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DimenUtil.pxFromDp(10)));
        mAnswerLayout.addView(space);

        quizAnswerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int answerId = mAnswerViews.indexOf(view);

                if(mMode == MODE_MULTIPLE_CHOICE) {
                    if (mQuizAdapter.isUserChecked(mQuizAdapter.getCurrentQuestion(), answerId)) {
                        mQuizAdapter.removeUserAnswer(mQuizAdapter.getCurrentQuestion(), answerId);
                    }else {
                        mQuizAdapter.addUserAnswer(mQuizAdapter.getCurrentQuestion(), answerId);
                    }
                }

                if(mMode == MODE_SINGLE_CHOICE) {
                    mQuizAdapter.clearUserAnswer(mQuizAdapter.getCurrentQuestion());
                    mQuizAdapter.addUserAnswer(mQuizAdapter.getCurrentQuestion(), answerId);
                }

                updateAnswerState();
            }
        });

        updateMode();

        mLinerLayout.invalidate();
        mLinerLayout.requestLayout();

        resetAnswerBackground();
    }

    public void updateAnswerState() {
        QuizModel quizModel = mQuizAdapter.getAt(mQuizAdapter.getCurrentQuestion());
        if(quizModel == null)
            return;

        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            mAnswerViews.get(i).setCheck(quizModel.getAnswers().get(i).isUserChoice());
        }
    }

    public void clearAnswerState() {
        for (int i=0; i<mAnswerViews.size(); i++) {
            mAnswerViews.get(i).setCheck(false);
        }
    }

    public QuizAdapter getAdapter() {
        return mQuizAdapter;
    }

    public void goTo(int stt) {
        QuizModel quizModel = mQuizAdapter.getAt(stt);
        if(quizModel == null)
            return;

        mAnswerViews.clear();
        mAnswerLayout.removeAllViews();

        setStt("Question " + String.valueOf(stt));
        setQuestion(quizModel.getQuestionContent());
        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            addAnswer(quizModel.getAnswers().get(i).getAnswerContent());
        }

        updateAnswerState();

        if(mIsShowResult) {
            resetAnswerBackground();
            showTrueResult();
            showFalseResult();
        }
    }

    public void goToWithResult(int stt) {
        goTo(stt);
        resetAnswerBackground();
        showTrueResult();
        showFalseResult();
    }

    public void showTrueResult() {
        QuizModel quizModel = mQuizAdapter.getAt(mQuizAdapter.getCurrentQuestion());
        if(quizModel == null)
            return;

        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            if(quizModel.getAnswers().get(i).isTrue()) {
                mAnswerViews.get(i).setBackground(mTrueDrawable);
            }
        }
    }

    public void showFalseResult() {
        QuizModel quizModel = mQuizAdapter.getAt(mQuizAdapter.getCurrentQuestion());
        if(quizModel == null)
            return;

        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            if(!quizModel.getAnswers().get(i).isTrue() && quizModel.getAnswers().get(i).isUserChoice) {
                mAnswerViews.get(i).setBackground(mFalseDrawable);
            }
        }
    }

    public void resetAnswerBackground() {
        for (int i=0; i<mAnswerViews.size(); i++) {
            mAnswerViews.get(i).setBackground(mNormalDrawable);
        }
    }

    public void read(String data) {
        Gson gson = new Gson();
        List<QuizModel> models = gson.fromJson(data, new TypeToken<List<QuizModel>>(){}.getType());

        mQuizAdapter.setData(models);
    }

    public void setIsShowResult(boolean isShow) {
        mIsShowResult = isShow;
    }
}
