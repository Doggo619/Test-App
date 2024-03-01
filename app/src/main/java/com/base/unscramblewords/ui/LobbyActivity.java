package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.base.unscramblewords.R;
import com.base.unscramblewords.databinding.ActivityLobbyBinding;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LobbyActivity extends AppCompatActivity {
    ActivityLobbyBinding binding;
    private static final long TIMER_DURATION = 10000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = TIMER_DURATION;
    private List<String> joinedParticipants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();



        String roomId = getIntent().getStringExtra("roomId");
        long participantId = getIntent().getLongExtra("participantId",0);
        String studentName = getIntent().getStringExtra("studentName");
        long participantsCount = getIntent().getLongExtra("participantsCount", 0);

        CollectionReference quizRef = db.collection("quiz");


        assert participantId != 0;
        quizRef.document(roomId).collection("participants").document(String.valueOf(participantId)).set(createParticipantMap(studentName));

        quizRef.document(roomId).collection("participants").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Log.w("Lobby Activity", "participants listen failed", e);
                return;
            }

            if (queryDocumentSnapshots != null) {
                String totalParticipants = String.valueOf(queryDocumentSnapshots.size());
                binding.tvTotalParticipants.setText(getString(R.string.students_joined, totalParticipants));

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            String newStudentName = (String) dc.getDocument().get("name");
                            long newParticipantId = Long.parseLong(dc.getDocument().getId());
                            if (newParticipantId > participantId) {
                                handleNewStudentJoined(newStudentName);
                            }
                            break;
//                        case MODIFIED:
//                            String newStudentName = (String) dc.getDocument().get("name");
//                            handleNewStudentJoined(newStudentName);
//                            break;
                    }
                }

            }
        });

        quizRef.document(roomId).addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                Log.w("Lobby Activity", "active listen failed", e);
                return;
            }

            if (documentSnapshot != null) {
                Boolean active = documentSnapshot.getBoolean("active");
                    if (Boolean.TRUE.equals(active)) {
                        startCountDownTimer(studentName, roomId, participantId, participantsCount, binding);
                    }

            }
        });

    }


    private void handleNewStudentJoined(String newStudentName) {

        joinedParticipants.add(newStudentName);

        Toast.makeText(this, newStudentName + " has joined", Toast.LENGTH_SHORT).show();

        final int[] currentIndex = {0};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex[0] < joinedParticipants.size()) {
                    String currentName = joinedParticipants.get(currentIndex[0]);
                    binding.tvStudentNameJoined.setText(currentName + " has joined");
                    currentIndex[0]++;
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(runnable);

    }


    private void startCountDownTimer(String studentName, String roomId, long participantId, long participantsCount, ActivityLobbyBinding binding) {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                binding.btnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btnActive)));
                binding.tvTimer.setVisibility(View.GONE);
                binding.tvHangOn.setText("You can start now");
                binding.btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LobbyActivity.this, QuizActivity.class);
                        intent.putExtra("studentName", studentName);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("participantId", participantId);
                        intent.putExtra("participantsCount", participantsCount);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                });
            }
        }.start();
    }

    private void updateTimerText() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) - TimeUnit.MINUTES.toSeconds(minutes);
        String timerText = String.format(Locale.US, "%02d:%02d", minutes, seconds);
        binding.tvTimer.setTextColor(getResources().getColor(R.color.green));
        binding.tvTimer.setText("Test will start in "+ timerText);
    }

    private Map<String, Object> createParticipantMap(String studentName) {
        Map<String, Object> participantMap = new HashMap<>();
        participantMap.put("name", studentName);
        return participantMap;
    }

}