package com.creitive.beoquiz;

import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Question[] mQuestionArray;
    private int mIndex = 0;

    private static final String INDEX_KEY = "com.creitive.beoquiz.index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mIndex = savedInstanceState.getInt(INDEX_KEY,0);
        }

        populateQuestionArray();

        Button btnFalse = (Button) findViewById(R.id.btnFalse);
        Button btnTrue = (Button) findViewById(R.id.btnTrue);
        ImageButton btnNext = (ImageButton) findViewById(R.id.btnNext);
        ImageButton btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        final TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        showQuestion(tvQuestion);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer(true)){
                    showQuestion(tvQuestion);
                }
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer(false)){
                    showQuestion(tvQuestion);
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = (mIndex+9)%10;
                showQuestion(tvQuestion);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = (mIndex+1)%10;
                showQuestion(tvQuestion);
            }
        });
    }

    private void showQuestion(TextView tvQuestion) {
        tvQuestion.setText(mQuestionArray[mIndex].getText());
    }

    private boolean checkAnswer(boolean answer) {
        if(mQuestionArray[mIndex].isAnswerTrue() == answer){
            mIndex = (mIndex+1)%10;
            Toast.makeText(this, R.string.question_correct, Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, R.string.question_incorrect, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override //Losa metoda, ne poziva se
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d("MainActivity","savedInstanceState");
        outState.putInt(INDEX_KEY,mIndex);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outPersistentState.putInt(INDEX_KEY,mIndex);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override //Ovo je metoda koju smo hteli da pozovemo
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INDEX_KEY,mIndex);
        Log.d("MainActivity","savedInstanceState");
        super.onSaveInstanceState(outState);
    }

    /*
            1. Beograd lezi na 30 brda
        2. Kalemegdan se nalazi na litici koja je visoka 300 metara
        3. Pobednik je sagradjen za 100 dana
        4. Pobednik je sagradjen 1928
        5. Hram Svetog Save se nalazi na Vracaru
        6. U Beogradu ima 1.3 miliona ljudi
        7. Novi Beograd ima vise stanovnika od Novog Sada
        8. Zemun se ranije zvao Taurunum
        9. Beograd je dobio ime po Ljubisi Preletacevicu Belom
        10. Na Tasmajdanu es nalazi pecina iz koje su kopali kamenje za pravljenje sarkofaga

             */
    private void populateQuestionArray() {
        mQuestionArray = new Question[10];
        mQuestionArray[0] = new Question(R.string.question1,true);
        mQuestionArray[1] = new Question(R.string.question2,false);
        mQuestionArray[2] = new Question(R.string.question3,false);
        mQuestionArray[3] = new Question(R.string.question4,true);
        mQuestionArray[4] = new Question(R.string.question5,true);
        mQuestionArray[5] = new Question(R.string.question6,true);
        mQuestionArray[6] = new Question(R.string.question7,false);
        mQuestionArray[7] = new Question(R.string.question8,true);
        mQuestionArray[8] = new Question(R.string.question9,true);
        mQuestionArray[9] = new Question(R.string.question10,true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestory");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onResume");
    }
}
