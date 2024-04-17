package com.devstromo.openaikotlin.chat.di

import com.devstromo.openaikotlin.chat.core.AndroidController
import com.devstromo.openaikotlin.chat.core.FakeController
import com.devstromo.openaikotlin.chat.data.AndroidGptController
import com.devstromo.openaikotlin.chat.data.FakeGptController
import com.devstromo.openaikotlin.chat.domain.GptController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @AndroidController
    fun provideGptController(): GptController {
        return AndroidGptController()
    }

    @Provides
    @FakeController
    fun provideFakeGptController(): GptController {
        return FakeGptController()
    }
}