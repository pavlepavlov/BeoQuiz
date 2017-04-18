package com.creitive.beoquiz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creitive.beoquiz.R;
import com.creitive.beoquiz.controller.QuizController;

public class MainActivity extends AppCompatActivity {

    private static final long SECOND = 1000;
    public static final String CHEAT = "com.creitive.beoquiz.cheat";

    private TextView tvQuestion;
    private QuizController mController;
    private Button btnFalse;
    private Button btnTrue;
    private Button btnSkip;
    private Button btnCheat;

    private EndQuizDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnCheat = (Button) findViewById(R.id.btnCheat);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        mController = new QuizController();
        mController.bind(this);
        mController.initialize();


        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.handleResult(true);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.handleResult(false);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.skipQuestion();
            }
        });

        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.cheat();
            }
        });
    }

    public void setQuestionText(String text) {
        tvQuestion.setText(text);
    }

    public void makeToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
        enableAllButtons(false);
        Handler handler = new Handler();
        final Runnable enableViews = new Runnable() {
            @Override
            public void run() {
                enableAllButtons(true);
            }
        };
        handler.postDelayed(enableViews, SECOND);
    }

    public void handleRestart() {
        mController.restart();
        enableAllButtons(true);
    }

    public void handleQuit() {
        this.finish();
    }

    public void showEndQuizDialog() {
        mDialog = new EndQuizDialog();
        mDialog.show(getFragmentManager(), "TAG");
        enableAllButtons(false);
    }

    private void enableAllButtons(final boolean enabled) {
        btnFalse.setEnabled(enabled);
        btnTrue.setEnabled(enabled);
        btnSkip.setEnabled(enabled);
    }

    @Override
    protected void onDestroy() {
        mController.saveCurrentState();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(mDialog != null){
            //nothing
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mController.onCheatResult(resultCode == Activity.RESULT_OK);
    }
}
