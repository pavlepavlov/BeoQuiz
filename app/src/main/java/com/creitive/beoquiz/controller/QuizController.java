package com.creitive.beoquiz.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.creitive.beoquiz.view.CheatActivity;
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
    public static final String CHEAT_KEY = "com.creitive.beoquiz.cheat";
    private static final int CHEAT_REQUEST = 37;

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
        mPrefs.edit().putBoolean(CHEAT_KEY,false).apply();
    }

    public void saveCurrentState() {
        String jsonQuiz = new Gson().toJson(mQuiz);
        mPrefs.edit().putString(INDEX_KEY,jsonQuiz).apply();
    }

    public void cheat() {
        if(!mPrefs.getBoolean(CHEAT_KEY,false)) {
            Intent intent = new Intent(mView, CheatActivity.class);
            intent.putExtra(CHEAT_KEY,mQuiz.getCurrentQuestion().isAnswerTrue());
            mView.startActivityForResult(intent,CHEAT_REQUEST);
        }
        else{
            Toast.makeText(mView, "Gde si posao druze?", Toast.LENGTH_SHORT).show();
        }

    }

    public void onCheatResult(boolean didCheat) {
        mPrefs.edit().putBoolean(CHEAT_KEY,didCheat).apply();
    }
}
