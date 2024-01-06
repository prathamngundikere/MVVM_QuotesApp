package com.prathamngundikere.quotes.quoteslist.domain.model

import com.prathamngundikere.quotes.quoteslist.data.remote.respond.QuoteDto

data class Quotes(
    val limit: Int,
    val quotes: List<QuoteDto>,
    val skip: Int,
    val total: Int
)