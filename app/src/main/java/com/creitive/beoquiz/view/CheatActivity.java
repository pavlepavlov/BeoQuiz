package com.creitive.beoquiz.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.creitive.beoquiz.R;
import com.creitive.beoquiz.controller.QuizController;

/**
 * Activity that shows correct answers
 */

public class CheatActivity extends AppCompatActivity {

    private static int sResultCode = Activity.RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        final boolean answer = getIntent().getBooleanExtra(QuizController.CHEAT_KEY,false);

        Button btnCheat = (Button) findViewById(R.id.btnCheat);
        final TextView tvAnswer = (TextView) findViewById(R.id.tvAnswer);

        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sResultCode = Activity.RESULT_OK;
                tvAnswer.setVisibility(View.VISIBLE);
                tvAnswer.setText(answer ? R.string.true_string : R.string.false_string);
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(sResultCode);
        super.onBackPressed();
    }
}
