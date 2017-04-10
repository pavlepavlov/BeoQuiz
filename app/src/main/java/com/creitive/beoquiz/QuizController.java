package com.creitive.beoquiz;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles user events and manages questions
 */

public class QuizController {

    private MainActivity mView;
    private List<Question> mList;
    private int mIndex = 0;

    public QuizController(){
        mList = new ArrayList<>();
    }

    public void initialize(){
        //TODO: Load index
        String[] questions = mView.getResources().getStringArray(R.array.questions);
        for(int i=0; i < questions.length; i++){
            Question question = new Question(questions[i],i < 7);
            mList.add(question);
        }
       displayCurrentQuestion();
    }

    private void displayCurrentQuestion() {
        mView.setQuestionText(mList.get(mIndex).getText());
    }

    public void bind(MainActivity view){
        mView = view;
    }

    public void skipQuestion() {
        nextQuestion();
    }

    public void handleResult(boolean isTrue) {
        if(mList.get(mIndex).isAnswerTrue() == isTrue){
            nextQuestion();
            mView.makeToast(R.string.true_string);
        }else{
            mView.makeToast(R.string.false_string);
        }
    }

    private void nextQuestion() {
        if(++mIndex >= mList.size()){
            mView.showEndQuizDialog();
        }
        else{
            displayCurrentQuestion();
        }
    }

    public void restart() {
        mIndex = 0;
        displayCurrentQuestion();
    }
}
