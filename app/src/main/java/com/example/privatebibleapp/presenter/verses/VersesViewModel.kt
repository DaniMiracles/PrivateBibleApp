package com.example.privatebibleapp.presenter.verses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.domain.verses.VersesDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VersesInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VersesViewModel(
    private val versesInteractor: VersesInteractor,
    private val mapper: VersesDomainToUiMapper,
    private val versesCommunication: VersesCommunication,
    private val navigator: VersesNavigator,
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Read<Int>
) : ViewModel() {

    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

    fun fetchVerses() {
        versesCommunication.map(listOf(VerseUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val versesDomain = versesInteractor.fetchBooks()
            val versesUi = versesDomain.map(mapper)
            withContext(dispatcherMain) {
                versesUi.map(versesCommunication)
            }
        }
    }

    fun observeVerses(owner: LifecycleOwner, observer: Observer<List<VerseUi>>) {
        versesCommunication.observe(owner, observer)
    }

    fun getTitle(): String = "${bookCache.read().second} Ch.${chapterCache.read()}"

    fun init() {
        navigator.saveVersesScreen()
        fetchVerses()
    }
}