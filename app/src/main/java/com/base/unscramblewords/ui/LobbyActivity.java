package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.base.unscramblewords.R;
import com.base.unscramblewords.databinding.ActivityLobbyBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LobbyActivity extends AppCompatActivity {
    ActivityLobbyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        String roomId = getIntent().getStringExtra("roomId");
        String participantId = getIntent().getStringExtra("participantId");
        String studentName = getIntent().getStringExtra("studentName");
        long participantsCount = getIntent().getLongExtra("participantsCount", 0);

        assert roomId != null;
        DocumentReference quizRef = db.collection("quiz").document(roomId);

        assert participantId != null;
        quizRef.collection("participants").document(participantId).set(createParticipantMap(studentName));

        quizRef.collection("participants").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Log.w("Lobby Activity", "participants listen failed", e);
                return;
            }

            if (queryDocumentSnapshots != null) {
                String totalParticipants = String.valueOf(queryDocumentSnapshots.size());
                binding.tvTotalStudents.setText(getString(R.string.students_joined, totalParticipants));
            }
        });

        quizRef.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                Log.w("Lobby Activity", "active listen failed", e);
                return;
            }

            if (documentSnapshot != null) {
                Boolean active = documentSnapshot.getBoolean("active");
                binding.btnStart.setOnClickListener(v -> {
                    if (Boolean.TRUE.equals(active)) {
                        Intent intent = new Intent(LobbyActivity.this, QuizActivity.class);
                        intent.putExtra("studentName", studentName);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("participantId", participantId);
                        intent.putExtra("participantsCount", participantsCount);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    } else {
                        Toast.makeText(LobbyActivity.this, "Please wait, Test will start soon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private Map<String, Object> createParticipantMap(String studentName) {
        Map<String, Object> participantMap = new HashMap<>();
        participantMap.put("name", studentName);
        return participantMap;
    }

}