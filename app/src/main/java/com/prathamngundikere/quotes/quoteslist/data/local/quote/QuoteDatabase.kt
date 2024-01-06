package com.prathamngundikere.quotes.quoteslist.data.local.quote

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
abstract class QuoteDatabase: RoomDatabase() {
    abstract val quoteDao: QuoteDao
}