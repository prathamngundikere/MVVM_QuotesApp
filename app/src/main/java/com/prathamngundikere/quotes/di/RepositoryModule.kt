package com.prathamngundikere.quotes.di

import com.prathamngundikere.quotes.quoteslist.data.repository.QuoteListRepositoryImpl
import com.prathamngundikere.quotes.quoteslist.domain.repository.QuoteListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindQuoteListRepository(
        quoteListRepositoryImpl: QuoteListRepositoryImpl
    ): QuoteListRepository
}