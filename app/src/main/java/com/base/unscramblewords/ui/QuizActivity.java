package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.base.unscramblewords.databinding.ActivityQuizBinding;
import com.base.unscramblewords.entity.quizEntity.Questions;
import com.base.unscramblewords.viewmodel.WordsViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity implements QuestionAdapter.OnClickListener {
    ActivityQuizBinding binding;
    private String roomId;
    private String participantId;
    private Map<Integer, Boolean> questionResults;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WordsViewModel viewModel = new ViewModelProvider(this).get(WordsViewModel.class);
        viewModel.insertQuestions();
        viewModel.getAllQuestions().observe(this, this::handleAllQuestions);

//        String studentName = getIntent().getStringExtra("studentName");
        roomId = getIntent().getStringExtra("roomId");
        participantId = getIntent().getStringExtra("participantId");
//        long participantsCount = getIntent().getLongExtra("participantsCount", 0);

        viewPager2 = binding.viewPager;
        questionResults = new HashMap<>();

        binding.btnFinish.setOnClickListener(v -> {
            saveResultsToDatabase(questionResults);
            Toast.makeText(QuizActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void saveResultsToDatabase(Map<Integer, Boolean> questionResults) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String participantPath = "quiz/" + roomId + "/participants/" + participantId;


        Map<String, Boolean> convertedResults = new HashMap<>();
        for (Map.Entry<Integer, Boolean> entry : questionResults.entrySet()) {
            convertedResults.put(String.valueOf(entry.getKey()), entry.getValue());
        }

        db.document(participantPath).update("results", convertedResults)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Answers Submitted", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(this, "Failed to submit results", Toast.LENGTH_SHORT).show());
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