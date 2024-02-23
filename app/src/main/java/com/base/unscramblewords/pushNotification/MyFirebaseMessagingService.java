package com.base.unscramblewords.pushNotification;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.base.unscramblewords.entity.quizEntity.Questions;
import com.base.unscramblewords.repository.WordsRepository;
import com.base.unscramblewords.storage.QuestionStorage;
import com.base.unscramblewords.viewmodel.WordsViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("Token", "token  " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);



        if (message.getData().size() > 0) {
            try {
                JSONArray notificationsArray = new JSONArray(message.getData().get("notifications"));
                List<Questions> questionsList = new ArrayList<>();
                for (int i = 0; i < notificationsArray.length(); i++) {
                    JSONObject notificationObject = notificationsArray.getJSONObject(i);
                    Questions question = processQuestionNotification(notificationObject);
                    questionsList.add(question);

                }
                QuestionStorage.getInstance().setQuestionsList(questionsList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("FCM", "Received message with no data");
        }
    }

    private Questions processQuestionNotification(JSONObject questionObject) throws JSONException {
        int questionId = questionObject.getInt("question_id");
        String question = questionObject.getString("question");
        String option1 = questionObject.getString("option1");
        String option2 = questionObject.getString("option2");
        String option3 = questionObject.getString("option3");
        String option4 = questionObject.getString("option4");
        String correctAnswer = questionObject.getString("correct_answer");
        Log.d("service", "check data  " + questionId + question);

        return new Questions(questionId, question, option1, option2, option3, option4 ,correctAnswer);


    }


}
