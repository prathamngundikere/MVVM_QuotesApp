package com.prathamngundikere.quotes.quoteslist.domain.repository

import com.prathamngundikere.quotes.quoteslist.domain.model.Quote
import com.prathamngundikere.quotes.quoteslist.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteListRepository {
    suspend fun getQuoteList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Quote>>>
}