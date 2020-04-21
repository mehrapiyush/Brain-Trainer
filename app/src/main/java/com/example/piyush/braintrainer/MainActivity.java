package com.example.piyush.braintrainer;

import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gobutton , button0, button1, button2, button3, playAgain;
    ConstraintLayout gameLayout;
    TextView correct , scoreText, timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgainHelper(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreText.setText("0" + "/" + "0");
        playAgain.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        correct.setVisibility(View.INVISIBLE);
        newQuestion();
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l){
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish(){
                correct.setText("time over");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);

            }

        }.start();

    }
    public void start(View v){
        gobutton.setVisibility(v.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgainHelper(timerTextView);
    }
    public void chooseAnswer(View v){
       String check = v.getTag().toString();

       correct.setVisibility(View.VISIBLE);
       if(check.equals(Integer.toString(locationOfCorrectAnswer))){
           correct.setText("Correct !");
           score++;
       }
       else
           correct.setText("Incorrect");
        numberOfQuestions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void newQuestion(){
        Random rand1 = new Random();
        Random rand2 = new Random();
        int a = rand1.nextInt(21);
        int b = rand2.nextInt(21);

        TextView sumTextView = (TextView)findViewById(R.id.sumTextView);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand1.nextInt(4);
        answers.clear();

        for(int i = 0 ; i < 4 ; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand1.nextInt(41);
                while(wrongAnswer == a+b)
                    wrongAnswer = rand1.nextInt(41);

                answers.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobutton = (Button)findViewById(R.id.goButton);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgain = (Button)findViewById(R.id.playAgain);
        correct = (TextView)findViewById(R.id.correctTextView);
        scoreText = (TextView)findViewById(R.id.scoreTextview);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

        gameLayout.setVisibility(View.INVISIBLE);
        gobutton.setVisibility(View.VISIBLE);
    }
}
