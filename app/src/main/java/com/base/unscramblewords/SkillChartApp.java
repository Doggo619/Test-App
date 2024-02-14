package com.base.unscramblewords;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.Configuration;

import com.base.unscramblewords.utils.SharedPrefHelper;

import java.io.File;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class SkillChartApp extends Application implements Configuration.Provider {
    private SharedPrefHelper sharedPrefHelper;
    public static String storageDirectoryRoot;
    @Inject
    HiltWorkerFactory workerFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPrefHelper = SharedPrefHelper.getInstance(getApplicationContext());
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
//        FirebaseInAppMessaging.getInstance().setAutomaticDataCollectionEnabled(true);
//
//        FirebaseMessaging.getInstance().subscribeToTopic(Constants.FIREBASE_APP_SUBSCRIPTION_TOPIC).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Common.putDebugLog("SubscribeToTopic ALL_APP_USERS - Success");
//            }
//        });


        storageDirectoryRoot = getExternalFilesDir(null).getPath() + File.separator;
    }


    @NonNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build();
    }
}
