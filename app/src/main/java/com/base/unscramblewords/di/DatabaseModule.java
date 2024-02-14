package com.base.unscramblewords.di;


import android.content.Context;

import androidx.room.Room;

import com.base.unscramblewords.database.AppDatabase;
import com.base.unscramblewords.utils.Common;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    static AppDatabase provideDatabase(@ApplicationContext Context applicationContext) {
        Common.putErrorLog("hilt data base created");
        return Room.databaseBuilder(applicationContext, AppDatabase.class, "SKILL_CHART_DATABASE_NAME")
                .fallbackToDestructiveMigration()
                .build();
    }
}
