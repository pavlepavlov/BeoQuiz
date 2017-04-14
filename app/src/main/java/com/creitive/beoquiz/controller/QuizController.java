package com.creitive.beoquiz.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.creitive.beoquiz.view.EndQuizDialog;
import com.creitive.beoquiz.view.MainActivity;
import com.creitive.beoquiz.R;
import com.creitive.beoquiz.model.Question;
import com.creitive.beoquiz.model.Quiz;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles user events and manages questions
 */

public class QuizController {

    private static final String INDEX_KEY = "com.creitive.beoquiz.index";

    private MainActivity mView;
    private Quiz mQuiz;
    private SharedPreferences mPrefs;

    public void initialize() {
        mPrefs = mView.getSharedPreferences(INDEX_KEY, Context.MODE_PRIVATE);

        String jsonQuiz = mPrefs.getString(INDEX_KEY,"");

        if(jsonQuiz.isEmpty()){
            restartQuiz();
        }
        else{
            mQuiz = new Gson().fromJson(jsonQuiz,Quiz.class);
        }
        displayCurrentQuestion();
    }

    private void restartQuiz() {
        mQuiz = new Quiz(loadQuestions());
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        String[] questions = mView.getResources().getStringArray(R.array.questions);
        for (int i = 0; i < questions.length; i++) {
            Question question = new Question(questions[i], i < 7);
            list.add(question);
        }
        return list;
    }

    private void displayCurrentQuestion() {
        mView.setQuestionText(mQuiz.getCurrentQuestion().getText());
    }

    public void bind(MainActivity view) {
        mView = view;
    }

    public void skipQuestion() {
        nextQuestion();
        mQuiz.incrementCurrentScore(Quiz.SKIP);
    }

    public void handleResult(boolean isTrue) {
        if (mQuiz.getCurrentQuestion().isAnswerTrue() == isTrue) {
            nextQuestion();
            mView.makeToast(R.string.true_string);
            mQuiz.incrementCurrentScore(Quiz.CORRECT);
        } else {
            mView.makeToast(R.string.false_string);
            mQuiz.incrementCurrentScore(Quiz.INCORRECT);
        }
    }

    private void nextQuestion() {
        if (!mQuiz.nextQuestion()) {
            EndQuizDialog.sCurrentScore = mQuiz.getCurrentScore();
            mView.showEndQuizDialog();
        } else {
            displayCurrentQuestion();
        }
    }

    public void restart() {
        restartQuiz();
        displayCurrentQuestion();
    }

    public void saveCurrentState() {
        String jsonQuiz = new Gson().toJson(mQuiz);
        mPrefs.edit().putString(INDEX_KEY,jsonQuiz).apply();
    }
}
