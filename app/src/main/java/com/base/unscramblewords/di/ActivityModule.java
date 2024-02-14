package com.base.unscramblewords.di;

import android.app.Application;
import android.content.Context;


import com.base.unscramblewords.database.AppDatabase;
import com.base.unscramblewords.repository.WordsRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityRetainedScoped;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class ActivityModule {

    @Provides
    @ActivityRetainedScoped
    static WordsRepository provideHomeRepository(Application application, Context context) {
        return new WordsRepository(application, context);
    }
}
