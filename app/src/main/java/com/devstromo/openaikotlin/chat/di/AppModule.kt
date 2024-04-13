package com.devstromo.openaikotlin.chat.di

import android.content.Context
import com.devstromo.openaikotlin.chat.data.AndroidGptController
import com.devstromo.openaikotlin.chat.domain.GptController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGptController(): GptController {
        return AndroidGptController()
    }
}