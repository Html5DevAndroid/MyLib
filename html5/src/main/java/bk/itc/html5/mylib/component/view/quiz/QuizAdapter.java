package bk.itc.html5.mylib.component.view.quiz;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hien on 5/21/2018.
 */

public class QuizAdapter {
    private List<QuizModel> mQuizModels = new ArrayList<>();
    private int mCurrentQuestion = 0;

    public boolean checkAnswer(int questionStt) {
        QuizModel quizModel = mQuizModels.get(questionStt);
        if(quizModel == null) {
            return false;
        }

        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            if(quizModel.getAnswers().get(i).isTrue() && !quizModel.getAnswers().get(i).isUserChoice()) {
                return false;
            }
            if(!quizModel.getAnswers().get(i).isTrue() && quizModel.getAnswers().get(i).isUserChoice()) {
                return false;
            }
        }

        return true;
    }

    public int numberOfUserTrueAnswer() {
        int count = 0;

        for (int i=0; i<mQuizModels.size(); i++) {
            if(checkAnswer(i)) {
                count++;
            }
        }

        return count;
    }

    public void addUserAnswer(int questionStt, int answerId) {
        QuizModel quizModel = getAt(questionStt);
        if(quizModel == null)
            return;

        if(answerId < 0 || answerId >= quizModel.getAnswers().size())
            return;

        quizModel.getAnswers().get(answerId).setUserChoice(true);
    }

    public void removeUserAnswer(int questionStt, int answerId) {
        QuizModel quizModel = getAt(questionStt);
        if(quizModel == null)
            return;

        if(answerId < 0 || answerId >= quizModel.getAnswers().size())
            return;

        quizModel.getAnswers().get(answerId).setUserChoice(false);
    }

    public void clearUserAnswer(int questionStt) {
        QuizModel quizModel = getAt(questionStt);
        if(quizModel == null)
            return;

        for (int i=0; i<quizModel.getAnswers().size(); i++) {
            quizModel.getAnswers().get(i).setUserChoice(false);
        }
    }

    public boolean isUserChecked(int questionStt, int answerId) {
        QuizModel quizModel = getAt(questionStt);
        if(quizModel == null)
            return false;

        if(answerId < 0 || answerId >= quizModel.getAnswers().size())
            return false;

        return quizModel.getAnswers().get(answerId).isUserChoice();
    }

    public QuizModel goNext() {
        return goAt(mCurrentQuestion+1);
    }

    public QuizModel goPrevious() {
        return goAt(mCurrentQuestion-1);
    }

    public QuizModel goAt(int questionStt) {
        if(questionStt >= 0 && questionStt < mQuizModels.size()) {
            mCurrentQuestion = questionStt;
            return mQuizModels.get(mCurrentQuestion);
        }

        return null;
    }

    public QuizModel getAt(int questionStt) {
        if(questionStt >= 0 && questionStt < mQuizModels.size()) {
            return mQuizModels.get(questionStt);
        }

        return null;
    }

    public int getCurrentQuestion() {
        return mCurrentQuestion;
    }

    public void setData(@NonNull List<QuizModel> models) {
        mQuizModels = models;
    }
}
