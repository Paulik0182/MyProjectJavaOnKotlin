package com.example.myprojectjavaonkotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KotlinActivity extends AppCompatActivity {

    private Button minusButton = null;
    private Button plusButton = null;
    private TextView textViewCounter = null;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_counter );

        minusButton = findViewById ( R.id.button_minus );
        plusButton = findViewById ( R.id.button_plus );
        textViewCounter = findViewById ( R.id.text_view_counter );

        counterUpdate(counter);

        minusButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                counterUpdate ( --counter );
            }
        } );

        plusButton.setOnClickListener ( v ->
                counterUpdate ( ++counter ) );
    }

    private  void counterUpdate (int counter){
        textViewCounter.setText ( String.valueOf ( counter ) );
    }
}