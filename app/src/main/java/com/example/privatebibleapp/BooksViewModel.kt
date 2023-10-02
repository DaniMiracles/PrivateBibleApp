package com.example.privatebibleapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privatebibleapp.domainBooks.BooksInteractor
import com.example.privatebibleapp.presenterBooks.BookUi
import com.example.privatebibleapp.presenterBooks.BooksDomainToUiMapper
import com.example.privatebibleapp.presenterBooks.BooksUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val booksDomainToUiMapper: BooksDomainToUiMapper,
    private val booksCommunication: BooksCommunication,
    private val uiDataCache: UiDataCache
) : ViewModel() {

    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO

    fun fetchBooks() {
        booksCommunication.map(listOf(BookUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            try {
                val resultDomain = booksInteractor.fetchBooks()
                val resultUi = resultDomain.map(booksDomainToUiMapper)
                (resultUi as BooksUi.Success).cache(uiDataCache)
                withContext(Dispatchers.Main) {
                    resultUi.map(booksCommunication)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        booksCommunication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        booksCommunication.map(uiDataCache.changeState(id))
    }
}