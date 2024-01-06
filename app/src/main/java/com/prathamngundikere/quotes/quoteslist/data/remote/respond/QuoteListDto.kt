package com.prathamngundikere.quotes.quoteslist.data.remote.respond

data class QuoteListDto(
    val limit: Int,
    val quotes: List<QuoteDto>,
    val skip: Int,
    val total: Int
)
