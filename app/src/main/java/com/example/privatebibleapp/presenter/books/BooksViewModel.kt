package com.example.privatebibleapp.presenter.books

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privatebibleapp.presenter.NavigationCommunication
import com.example.privatebibleapp.core.Save
import com.example.privatebibleapp.presenter.Screens.Companion.BOOKS_SCREEN
import com.example.privatebibleapp.presenter.Screens.Companion.CHAPTERS_SCREEN
import com.example.privatebibleapp.domain.books.BooksInteractor
import com.example.privatebibleapp.domain.books.BooksDomainToUiMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val booksDomainToUiMapper: BooksDomainToUiMapper,
    private val booksCommunication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Pair<Int, String>>,
    private val navigator: Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel() {

    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

    fun fetchBooks() {
        booksCommunication.map(listOf(BookUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            try {
                val resultDomain = booksInteractor.fetchBooks()
                val resultUi = resultDomain.map(booksDomainToUiMapper)
                val actual = (resultUi as BooksUi.Base).cache(uiDataCache)
                withContext(dispatcherMain) {
                    actual.map(booksCommunication)
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

    fun saveCollapsedStates() = uiDataCache.saveState()

    fun showBook(id: Int, name: String) {
        bookCache.save(Pair(id, name))
        navigationCommunication.map(CHAPTERS_SCREEN)
    }

    fun init() {
        navigator.save(BOOKS_SCREEN)
        fetchBooks()
    }
}