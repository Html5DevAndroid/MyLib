package bk.itc.html5.mylib.component.view.quiz;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hien on 5/21/2018.
 */

public class QuizModel {
    String questionContent;
    List<QuizAnswerModel> answers = new ArrayList<>();

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<QuizAnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswerModel> answers) {
        this.answers = answers;
    }
}
