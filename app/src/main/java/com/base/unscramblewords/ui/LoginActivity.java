package com.base.unscramblewords.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.base.unscramblewords.databinding.ActivityLoginBinding;
import com.base.unscramblewords.fireStoreDatabase.Students;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private DocumentReference quizRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        quizRef = db.collection("quiz").document();

        binding.btnQuiz.setOnClickListener(v -> {
            String studentName = Objects.requireNonNull(binding.etPlayerName.getText()).toString().trim();
            String roomId = Objects.requireNonNull(binding.etRoomID.getText()).toString().trim();
            joinOrCreateQuizRoom(studentName, roomId);
        });

    }

    private void joinOrCreateQuizRoom(String studentName, String roomId) {
        CollectionReference currentQuizRef = quizRef.collection("rooms");
        DocumentReference roomDocumentRef = currentQuizRef.document(roomId);

        roomDocumentRef.collection("participants").get().addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               QuerySnapshot snapshot = task.getResult();
               if (snapshot != null) {
                   long participantsCount = snapshot.size();
                   long MAX_PARTICIPANTS = 5000;
                   if (participantsCount < MAX_PARTICIPANTS) {
                       joinOrCreateQuizRoomCount(studentName, roomId, roomDocumentRef, participantsCount);
                   } else {
                       Toast.makeText(LoginActivity.this, "Room is full", Toast.LENGTH_SHORT).show();
                   }
               }
           } else {
               Log.d("Login Activity", "Error getting documents: ", task.getException());
           }
        });
    }

    private void joinOrCreateQuizRoomCount(String studentName, String roomId, DocumentReference currentQuizRef, long participantsCount) {

        currentQuizRef.collection("rooms").document(roomId).get().addOnSuccessListener( documentSnapshot -> {
            if (documentSnapshot.exists()) {
                joinRoomAsStudent(studentName, roomId, currentQuizRef, participantsCount);
            } else {
                createRoomAsStudent(studentName, roomId, currentQuizRef, participantsCount);
            }
        }).addOnFailureListener(e -> Log.d("Login Activity", "onFailure: " + e.getMessage()));
    }

    private void createRoomAsStudent(String studentName, String roomId, DocumentReference  currentQuizRef, long participantsCount) {
        String participantId = currentQuizRef.collection("rooms").document(roomId).collection("participants").document().getId();
        currentQuizRef.collection("rooms").document(roomId).collection("participants").document(participantId).set(new Students(studentName));
        currentQuizRef.collection("rooms").document(roomId).update("totalParticipants", participantsCount + 1);
        startQuizActivity(studentName, roomId, participantId, participantsCount + 1);
    }

    private void joinRoomAsStudent(String studentName, String roomId, DocumentReference  currentQuizRef, long participantsCount) {
        String participantId = currentQuizRef.collection("rooms").document(roomId).collection("participants").document().getId();
        currentQuizRef.collection("rooms").document(roomId).collection("participants").document(participantId).set(new Students(studentName));
        currentQuizRef.collection("rooms").document(roomId).update("totalParticipants", participantsCount + 1);
        startQuizActivity(studentName, roomId, participantId, participantsCount + 1);
    }

    private void startQuizActivity(String studentName, String roomId, String participantId, long participantsCount) {
        Intent intent = new Intent(LoginActivity.this, LobbyActivity.class);
        intent.putExtra("studentName", studentName);
        intent.putExtra("roomId", roomId);
        intent.putExtra("participantId", participantId);
        intent.putExtra("participantsCount", participantsCount);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }


}