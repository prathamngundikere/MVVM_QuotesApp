package com.prathamngundikere.quotes.quoteslist.presentation

import com.prathamngundikere.quotes.quoteslist.domain.model.Quote

data class QuoteListState(
    val isLoading: Boolean = false,
    val allQuoteList: List<Quote> = emptyList()
)
