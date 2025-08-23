package com.example.baseapp.core.di

import com.example.baseapp.core.helpers.retry.DefaultRetryController
import com.example.baseapp.core.helpers.retry.RetryController
import com.example.baseapp.features.login.data.remote.LoginApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.seu-backend.com/") // Troque pela URL real
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetryController(): RetryController {
        return DefaultRetryController(3)
    }
}