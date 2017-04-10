package com.creitive.beoquiz;

/**
 * This class represents one question in the quiz
 */

public class Question {
    private String mText;
    private boolean mAnswerTrue;

    public Question(String text,boolean answerTrue){
        mText = text;
        mAnswerTrue = answerTrue;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
