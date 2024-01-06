package com.prathamngundikere.quotes.di

import android.app.Application
import androidx.room.Room
import com.prathamngundikere.quotes.quoteslist.data.local.quote.QuoteDatabase
import com.prathamngundikere.quotes.quoteslist.data.remote.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    @Provides
    @Singleton
    fun providesQuotesApi(): QuoteApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(QuoteApi.BASE_URL)
            .client(client)
            .build()
            .create(QuoteApi::class.java)
    }
    @Provides
    @Singleton
    fun providesQuotesDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            "quote.db",
        ).build()
    }
}