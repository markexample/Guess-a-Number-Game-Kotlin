package com.daltontechnologies.guessanumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Main activity used in this Guess a Number game app
 *
 * @author Mark Dalton
 *
 */
@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    /**
     * Text to display number of tries the user guesses a number
     */
    private TextView mNumTries;

    /**
     * Text input for the user to enter a guessed number
     */
    private EditText mEditText;

    /**
     * Submit button the user presses after entering a number
     */
    private Button mSubmit;

    /**
     * Play again button the user presses after the game ends
     */
    private Button mPlayAgain;

    /**
     * Message displayed when the user guesses a number below the random number
     */
    private final String TOO_LOW = "Try Again. Your number is too low!";

    /**
     * Message displayed when the user guesses a number above the random number
     */
    private final String TOO_HIGH = "Try Again. Your number is too high!";

    /**
     * Message displayed when the user guesses the correct random number
     */
    private final String CORRECT = "Congrats! You are correct!";

    /**
     * Maximum limit for the random number
     */
    private final int MAX_NUM = 100;

    /**
     * Minimum limit for the random number
     */
    private final int MIN_NUM = 1;

    /**
     * Random number guessed in game
     */
    private int randomNumber = 0;

    /**
     * Number of tries the user guesses a number
     */
    private int numOfTries = 0;

    /**
     * Prefix for message displaying the number of tries the user guesses a number
     */
    private final String NUM_OF_TRIES_PREFIX = "# of tries: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view to xml layout file for this main activity
        setContentView(R.layout.activity_main);

        //set number of tries text view from layout file
        mNumTries = findViewById(R.id.num_tries);

        //set edit text from layout file
        mEditText = findViewById(R.id.edit_text);

        //set submit button from layout file
        mSubmit = findViewById(R.id.submit);

        //set play again button from layout file
        mPlayAgain = findViewById(R.id.play_again);

        //generate random number used for first round of game
        generateRandomNumber();

        //set submit button click listener
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //submit a guessed number
                submit();
            }
        });

        //set play again button click listener
        mPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //play another round of the game
                playAgain();
            }
        });

    }

    /**
     * Generate a random number
     */
    private void generateRandomNumber(){

        //the random number set is between and including the min and max limits
        randomNumber = new Random().nextInt(MAX_NUM) + MIN_NUM;
    }

    /**
     * Procedure for resetting the game to play again
     */
    private void playAgain(){

        //generate a new random number
        generateRandomNumber();

        //hide play again button
        mPlayAgain.setVisibility(View.GONE);

        //reset the number of tries
        setNumTries(0);

        //clear out the input text for a new game
        mEditText.setText("");
    }

    /**
     * Submit a guessed number to see the result
     */
    private void submit(){

        //get the guessed number string from the text input
        String editTextString = mEditText.getText().toString();

        //check to skip guess attempt when this string is empty
        if(editTextString.isEmpty()){ return; }

        //convert string into an integer
        int currentNum = Integer.parseInt(editTextString);

        //message to display result of the guess
        String message;

        //check if the guessed number is the random number
        if(currentNum == randomNumber){

            //set correct result message
            message = CORRECT;

            //show the play again button
            mPlayAgain.setVisibility(View.VISIBLE);
        }

        //else the guessed number is not correct
        else{

            //increment the number of tries by 1
            setNumTries(numOfTries + 1);

            //check if guessed number is less than random number
            //and set result message as too low or high accordingly
            message = currentNum < randomNumber ? TOO_LOW : TOO_HIGH;

            //clear out the text input for another guess
            mEditText.setText("");
        }

        //display a long toast with the result of the guess
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    /**
     * Set the number of tries on
     * @param number the new number of tries attempted
     */
    private void setNumTries(int number){

        //set number of tries to new value
        numOfTries = number;

        //convert the integer parameter to a string
        String newNumbOfTries = String.valueOf(number);

        //concatenate the number of tries prefix to the new number of tries
        String x = NUM_OF_TRIES_PREFIX + newNumbOfTries;

        //set the number of tries text to display the new number of tries
        mNumTries.setText(x);
    }

}