package com.creitive.beoquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Quiz holds list of questions and remembers the score
 */

public class Quiz {

    public static final int CORRECT = 1;
    public static final int SKIP = 2;
    public static final int INCORRECT = 3;

    private List<Question> mList;
    private int mCurrentScore = 0;

    public Quiz(List<Question> list) {
        mList = list;
        Collections.shuffle(mList);
    }

    public Question getCurrentQuestion() {
        return mList.get(0);
    }

    public boolean nextQuestion() {
        if (mList.size() > 1) {
            mList.remove(0);
            return true;
        }
        return false;
    }

    public void incrementCurrentScore(int increment) {
        mCurrentScore += increment;
    }

    public int getCurrentScore() {
        return mCurrentScore;
    }
}
