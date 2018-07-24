package bk.itc.html5.mylib.component.view.quiz;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hien on 5/21/2018.
 */

public class QuizModel {
    String questionContent;
    String questionImage;
    String questionMp3;
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

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public String getQuestionMp3() {
        return questionMp3;
    }

    public void setQuestionMp3(String questionMp3) {
        this.questionMp3 = questionMp3;
    }
}
