package com.prathamngundikere.quotes.quoteslist.data.remote

import com.prathamngundikere.quotes.quoteslist.data.remote.respond.QuoteListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
    @GET("quotes")
    suspend fun getQuotesList(
        @Query("skip") skip:Int = 0,
        @Query("limit") limit:Int = 1454
    ): QuoteListDto
    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}