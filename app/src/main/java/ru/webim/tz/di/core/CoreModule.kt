package ru.webim.tz.di.core

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import ru.webim.tz.data.cache.implementation.UserCacheDataSourceImpl
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideCache(
        source: SharedPreferences
    ): UserCacheDataSource {
        return UserCacheDataSourceImpl(source)
    }

    @Provides
    @Singleton
    fun providesPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

}