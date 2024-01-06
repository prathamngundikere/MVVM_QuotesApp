package com.prathamngundikere.quotes.quoteslist.data.mappers

import com.prathamngundikere.quotes.quoteslist.data.local.quote.QuoteEntity
import com.prathamngundikere.quotes.quoteslist.data.remote.respond.QuoteDto
import com.prathamngundikere.quotes.quoteslist.domain.model.Quote

fun QuoteEntity.toQuote(): Quote {
    return Quote(
        author = author,
        quote = quote,
        id = id
    )
}
fun QuoteDto.toQuoteEntity(): QuoteEntity {
    return QuoteEntity(
        author = author?: "",
        quote = quote?: "",
        id = id?: -1
    )
}