package com.prathamngundikere.quotes.quoteslist.data.local.quote

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface QuoteDao {
    @Upsert
    suspend fun upsertQuoteList(quoteList: List<QuoteEntity>)
    @Query("SELECT * FROM QuoteEntity")
    suspend fun getAllQuotes(): List<QuoteEntity>
}