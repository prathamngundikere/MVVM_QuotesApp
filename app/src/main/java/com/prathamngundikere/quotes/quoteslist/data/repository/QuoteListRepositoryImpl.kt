package com.prathamngundikere.quotes.quoteslist.data.repository

import com.prathamngundikere.quotes.quoteslist.data.local.quote.QuoteDatabase
import com.prathamngundikere.quotes.quoteslist.data.mappers.toQuote
import com.prathamngundikere.quotes.quoteslist.data.mappers.toQuoteEntity
import com.prathamngundikere.quotes.quoteslist.data.remote.QuoteApi
import com.prathamngundikere.quotes.quoteslist.domain.model.Quote
import com.prathamngundikere.quotes.quoteslist.domain.repository.QuoteListRepository
import com.prathamngundikere.quotes.quoteslist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class QuoteListRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi,
    private val quoteDatabase: QuoteDatabase
): QuoteListRepository {
    override suspend fun getQuoteList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading(true))
            val localQuoteList = quoteDatabase.quoteDao.getAllQuotes()
            val shouldLoadLocalQuote = localQuoteList.isNotEmpty() && !forceFetchFromRemote
            if(shouldLoadLocalQuote) {
                emit(Resource.Success(
                    data = localQuoteList.map { quoteEntity ->
                        quoteEntity.toQuote()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val quoteListFromApi = try {
                quoteApi.getQuotesList()
            }  catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Quotes"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Quotes"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Quotes"))
                return@flow
            }
            val quoteEntities = quoteListFromApi.quotes.let {
                it.map {QuoteDto ->
                    QuoteDto.toQuoteEntity()
                }
            }
            quoteDatabase.quoteDao.upsertQuoteList(quoteEntities)
            emit(Resource.Success(
                quoteEntities.map { it.toQuote() }
            ))
            emit(Resource.Loading(false))
        }
    }
}