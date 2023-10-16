package com.example.privatebibleapp.presenter.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.core.Save
import com.example.privatebibleapp.domain.chapters.ChaptersInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.privatebibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.privatebibleapp.presenter.NavigationCommunication
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersDomainToUiMapper: ChaptersDomainToUiMapper,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel(), Show {

    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

    fun observeChapters(owner: LifecycleOwner, observer: Observer<List<ChapterUi>>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(dispatcherIO) {
            try {
                val chaptersDomain = chaptersInteractor.fetchChapters()
                val chaptersUi = chaptersDomain.map(chaptersDomainToUiMapper)
                withContext(dispatcherMain) {
                    chaptersUi.map(chaptersCommunication)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    fun getBookName() = bookCache.read().second

    override fun show(id: Int) {
        chapterCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }
}

interface Show {

    fun show(id: Int)
}