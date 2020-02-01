package com.daltontechnologies.guessanumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mNumTries;
    private EditText mEditText;
    private Button mSubmit, mPlayAgain;
    private final String TOO_LOW = "Try Again. Your number is too low!";
    private final String TOO_HIGH = "Try Again. Your number is too high!";
    private final String CORRECT = "Congrats! You are correct!";
    private final int MAX_NUM = 100;
    private final int MIN_NUM = 1;
    private int randomNumber = 0;
    private int numOfTries = 0;
    private final String NUM_OF_TRIES_PREFIX = "# of tries: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumTries = findViewById(R.id.num_tries);
        mEditText = findViewById(R.id.edit_text);
        mSubmit = findViewById(R.id.submit);
        mPlayAgain = findViewById(R.id.play_again);

        generateRandomNumber();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        mPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

    }

    private void generateRandomNumber(){
        randomNumber = new Random().nextInt(MAX_NUM) + MIN_NUM;
    }

    private void playAgain(){
        generateRandomNumber();
        mPlayAgain.setVisibility(View.GONE);
        setNumTries(0);
        mEditText.setText("");
    }

    private void submit(){

        String editTextString = mEditText.getText().toString();

        if(editTextString.isEmpty()){ return; }

        int currentNum = Integer.parseInt(editTextString);

        String message = "";

        if(currentNum == randomNumber){
            message = CORRECT;
            mPlayAgain.setVisibility(View.VISIBLE);
        }else if(currentNum < randomNumber){
            message = TOO_LOW;
            setNumTries(numOfTries + 1);
        }else{
            message = TOO_HIGH;
            setNumTries(numOfTries + 1);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        mEditText.setText("");

    }

    private void setNumTries(int number){
        numOfTries = number;
        mNumTries.setText(NUM_OF_TRIES_PREFIX + String.valueOf(number));
    }

}

//EXTRA LINES

























































//END OF EXTRA LINES