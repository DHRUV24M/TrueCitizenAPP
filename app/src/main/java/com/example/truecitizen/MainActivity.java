package com.example.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.truecitizen.databinding.ActivityMainBinding;
import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex;
    private Question[] questionsBank = new Question[]{
            // create question object
            new Question(R.string.question_1,true),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,false),
            new Question(R.string.question_5,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to access all the widgets of app
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);



        binding.nextButton.setOnClickListener(view -> {
            // display question to textView forward
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsBank.length;
            updateQuestion(currentQuestionIndex);
        });

        binding.prevButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex - 1 + questionsBank.length) % questionsBank.length;
            updateQuestion((currentQuestionIndex));
        });

        // setting true of false button
        binding.trueButton.setOnClickListener(view -> {
                trueOrFalse(true);
        });

        binding.falseButton.setOnClickListener(view -> {
            trueOrFalse((false));
        });

    }

    private void trueOrFalse(boolean userChosen)
    {
        boolean isAnswerCorrect = questionsBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(isAnswerCorrect == userChosen)
        {
            messageId = R.string.correct_answer;
        }
        else
        {
            messageId = R.string.incorrect_answer;
        }
        Snackbar.make(binding.imageView,messageId,Snackbar.LENGTH_SHORT).show();

    }
    private void updateQuestion(int currentQuestionIndex) {
        binding.questionTextView.setText(questionsBank[currentQuestionIndex].getAnswerResId());
    }
}