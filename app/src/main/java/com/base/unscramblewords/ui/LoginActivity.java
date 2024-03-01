package com.base.unscramblewords.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.base.unscramblewords.databinding.ActivityLoginBinding;
import com.base.unscramblewords.fireStoreDatabase.Students;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private CollectionReference quizRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseMessaging.getInstance().subscribeToTopic("test")
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       Log.d("LoginActivity", "Successfully subscribed to topic");
                   } else {
                       Log.e("LoginActivity", "Failed to subscribe to topic", task.getException());
                   }
                });

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        quizRef = db.collection("quiz");

        String roomId = "6699";
        binding.btnQuiz.setOnClickListener(v -> {
            String studentName = Objects.requireNonNull(binding.etPlayerName.getText()).toString().trim();
            joinOrCreateQuizRoom(studentName, roomId);
        });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult();
                            Log.d("Main", "Token: " + token);
                        } else {
                            Log.e("Main", "Error getting FCM token: " + task.getException());
                        }
                    }
                });
    }

    private void joinOrCreateQuizRoom(String studentName, String roomId) {

        DocumentReference roomDocumentRef = quizRef.document(roomId);

        roomDocumentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    roomDocumentRef.collection("participants").get().addOnCompleteListener(participantsTask -> {
                        if (participantsTask.isSuccessful()) {
                            QuerySnapshot snapshot = participantsTask.getResult();
                            if (snapshot != null) {
                                long participantsCount = snapshot.size();
                                joinRoomAsStudent(studentName, roomId, roomDocumentRef, participantsCount);
                            } else {
                                Log.d("Login Activity", "Error getting documents: ", task.getException());
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Room doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void joinRoomAsStudent(String studentName, String roomId, DocumentReference roomDocumentRef, long participantsCount) {
        String participantId = String.valueOf(participantsCount +1);
        roomDocumentRef.collection("participants").document(participantId).set(new Students(studentName));
        roomDocumentRef.update("totalParticipants", participantsCount + 1);
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MainActivity", "onNewIntent: " + intent.getAction());
    }
}