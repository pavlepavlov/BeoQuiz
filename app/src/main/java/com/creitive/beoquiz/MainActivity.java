package com.creitive.beoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFalse = (Button) findViewById(R.id.btnFalse);
        Button btnTrue = (Button) findViewById(R.id.btnTrue);
        final TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Tacan odgovor",Toast.LENGTH_LONG).show();
                tvQuestion.setText("Pobednik je na Kalemegdanu");
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Netacan odgovor",Toast.LENGTH_LONG).show();
            }
        });


    }
}
