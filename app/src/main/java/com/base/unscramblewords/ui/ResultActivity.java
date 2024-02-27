package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.base.unscramblewords.R;
import com.base.unscramblewords.databinding.ActivityQuizBinding;
import com.base.unscramblewords.databinding.ActivityResultBinding;

import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    private int totalQuestions;
    private int answeredQuestions = 0;
    private int correctAnswers = 0;
    private long timeTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        totalQuestions = getIntent().getIntExtra("totalNoOfQuestions", 0);
        timeTaken = getIntent().getLongExtra("timeTaken", 0);

        Bundle bundle = getIntent().getBundleExtra("questionResultsBundle");
        if (bundle != null) {
            Map<Integer, String> questionResults = (Map<Integer, String>) bundle.getSerializable("questionResults");
            for (Map.Entry<Integer, String> entry : questionResults.entrySet()) {
                answeredQuestions++;
                switch (entry.getValue()) {
                    case "Correct":
                        correctAnswers++;
                        break;
                    case "UnAnswered":
                        answeredQuestions--;
                        break;
                }
            }
        }

        Log.d("Result", "totalQuestions  " + totalQuestions + "unAnsweredQuestions " + answeredQuestions + "correctAnswers " + correctAnswers + "timeTaken " + timeTaken);

        long totalMilliseconds = timeTaken;

        long totalSeconds = totalMilliseconds / 1000;

        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        String formattedTime = minutes + " mins " + seconds + " secs";

        binding.tvTotalQuestionsNumber.setText(String.valueOf(totalQuestions));
        binding.tvTotalQuestionsAttemptedNumber.setText(String.valueOf(answeredQuestions));
        binding.tvCorrectAnswersNumber.setText(String.valueOf(correctAnswers));
        binding.tvTimeTakenNumber.setText(formattedTime);

        binding.btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultActivity.this, "Yet to design", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}