package com.creitive.beoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String INDEX_KEY = "com.creitive.beoquiz.index";

    private TextView tvQuestion;
    private QuizController mController;
    private Button btnFalse;
    private Button btnTrue;
    private Button btnSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnSkip = (Button) findViewById(R.id.btnSkip);
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
    }

    public void setQuestionText(String text) {
        tvQuestion.setText(text);
    }

    public void makeToast(int stringId) {
        //Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    public void handleRestart() {
        mController.restart();
        enableAllButtons(true);
    }

    public void handleQuit() {
        this.finish();
    }

    public void showEndQuizDialog() {
        EndQuizDialog dialog = new EndQuizDialog();
        dialog.show(getFragmentManager(), "TAG");
        enableAllButtons(false);
    }

    private void enableAllButtons(boolean enabled) {
        btnFalse.setEnabled(enabled);
        btnTrue.setEnabled(enabled);
        btnSkip.setEnabled(enabled);
    }
}
