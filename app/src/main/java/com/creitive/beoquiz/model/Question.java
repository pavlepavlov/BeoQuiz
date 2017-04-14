package com.creitive.beoquiz.model;

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

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

}
