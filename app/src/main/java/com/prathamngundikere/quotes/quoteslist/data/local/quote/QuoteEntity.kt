package com.prathamngundikere.quotes.quoteslist.data.local.quote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    val author: String,
    val quote: String,
    @PrimaryKey
    val id: Int
)
