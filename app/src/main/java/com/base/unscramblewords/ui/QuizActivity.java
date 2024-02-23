package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.base.unscramblewords.databinding.ActivityQuizBinding;
import com.base.unscramblewords.entity.quizEntity.Questions;
import com.base.unscramblewords.pushNotification.MyFirebaseMessagingService;
import com.base.unscramblewords.storage.QuestionStorage;
import com.base.unscramblewords.viewmodel.WordsViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity implements QuestionAdapter.OnClickListener {
    ActivityQuizBinding binding;
    private String roomId;
    private String participantId;
    private Map<Integer, Boolean> questionResults;
    ViewPager2 viewPager2;
    private static final long TIMER_DURATION = 600000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = TIMER_DURATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startCountDownTimer();

        WordsViewModel viewModel = new ViewModelProvider(this).get(WordsViewModel.class);
//        viewModel.insertQuestions();

        QuestionStorage questionStorage = QuestionStorage.getInstance();
        List<Questions> questionsList = questionStorage.getQuestionsList();
        viewModel.insertQuestions(questionsList);
        viewModel.getAllQuestions().observe(this, this::handleAllQuestions);

//        String studentName = getIntent().getStringExtra("studentName");
        roomId = getIntent().getStringExtra("roomId");
        participantId = getIntent().getStringExtra("participantId");
//        long participantsCount = getIntent().getLongExtra("participantsCount", 0);

        viewPager2 = binding.viewPager;
        questionResults = new HashMap<>();

        binding.btnFinish.setOnClickListener(v -> {
            String timeTaken = binding.tvTimer.getText().toString();
            Log.d("quiz Activity", "Time Taken   " + timeTaken);
            countDownTimer.cancel();
            saveResultsToDatabase(questionResults, timeTaken);
            sendResultsToWhatsApp();
            Intent intent = new Intent(QuizActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void sendResultsToWhatsApp() {

    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                String timeTaken = binding.tvTimer.getText().toString();
                saveResultsToDatabase(questionResults, timeTaken);
                Toast.makeText(QuizActivity.this, "Time's up! Test submitted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuizActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void updateTimerText() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) - TimeUnit.MINUTES.toSeconds(minutes);
        String timerText = String.format(Locale.US, "%02d:%02d", minutes, seconds);
        binding.tvTimer.setText(timerText);
    }

    private void saveResultsToDatabase(Map<Integer, Boolean> questionResults, String timeTaken) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String participantPath = "quiz/" + roomId + "/participants/" + participantId;


        Map<String, Boolean> convertedResults = new HashMap<>();
        for (Map.Entry<Integer, Boolean> entry : questionResults.entrySet()) {
            convertedResults.put(String.valueOf(entry.getKey()), entry.getValue());
        }

        long timeTakenInMilliSeconds = TIMER_DURATION - convertTimeToSeconds(timeTaken);

        Map<String, Object> participantData = new HashMap<>();
        participantData.put("results", convertedResults);
        participantData.put("timeTaken (in MilliSeconds)", timeTakenInMilliSeconds);

        db.document(participantPath).update(participantData)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Answers Submitted Successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to Submit test", Toast.LENGTH_SHORT).show());
    }

    private long convertTimeToSeconds(String timeTaken) {
        String[] timeComponents = timeTaken.split(":");
        if (timeComponents.length == 2) {
            long minutes = Long.parseLong(timeComponents[0]);
            long seconds = Long.parseLong(timeComponents[1]);
            long minutesInSeconds = minutes * 60;
            long totalSeconds = minutesInSeconds + seconds;
            return totalSeconds * 1000;
        } else {
            return 0;
        }
    }

    private void handleAllQuestions(List<Questions> questions) {
        if (questions != null) {
            QuestionAdapter adapter = new QuestionAdapter(this, this, viewPager2);
            viewPager2.setAdapter(adapter);

            adapter.submitList(questions);

        }
    }

    @Override
    public void onClick(int questionId, Boolean isCorrect) {
        questionResults.put(questionId, isCorrect);
    }

}