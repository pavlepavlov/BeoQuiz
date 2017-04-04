package com.creitive.beoquiz;

/**
 * This class represents one question in the quiz
 */

public class Question {
    private int mText;
    private boolean mAnswerTrue;

    public Question(int text,boolean answerTrue){
        mText = text;
        mAnswerTrue = answerTrue;
    }

    public int getText() {
        return mText;
    }

    public void setText(int text) {
        mText = text;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
