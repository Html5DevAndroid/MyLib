package bk.itc.html5.mylib.component.view.quiz;

/**
 * Created by Hien on 5/21/2018.
 */

public class QuizAnswerModel {
    String answerContent;
    boolean isTrue;
    boolean isUserChoice;

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public boolean isUserChoice() {
        return isUserChoice;
    }

    public void setUserChoice(boolean choice) {
        isUserChoice = choice;
    }
}
