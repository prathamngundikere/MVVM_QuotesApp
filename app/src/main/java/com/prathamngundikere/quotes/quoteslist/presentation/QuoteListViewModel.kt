package com.prathamngundikere.quotes.quoteslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamngundikere.quotes.quoteslist.domain.repository.QuoteListRepository
import com.prathamngundikere.quotes.quoteslist.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val quoteListRepository: QuoteListRepository
): ViewModel() {
    private var _quoteListState = MutableStateFlow(QuoteListState())
    val quoteListState = _quoteListState.asStateFlow()
    init {
        getAllQuoteList(false)
    }
    private fun getAllQuoteList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _quoteListState.update {
                it.copy(isLoading = true)
            }
            quoteListRepository.getQuoteList(forceFetchFromRemote).collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _quoteListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _quoteListState.update {
                            it.copy(result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { QuoteList ->
                            _quoteListState.update {
                                it.copy(
                                    allQuoteList = quoteListState
                                        .value
                                        .allQuoteList + QuoteList
                                        .shuffled()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}