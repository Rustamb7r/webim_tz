package ru.webim.tz.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.webim.tz.stash.source.UserCacheSource
import ru.webim.tz.stash.implementation.UserCacheImplementation
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideCache(
        source: SharedPreferences
    ): UserCacheSource {
        return UserCacheImplementation(source)
    }

    @Provides
    @Singleton
    fun providesPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

}