package com.example.privatebibleapp.core

import android.app.Application
import com.example.privatebibleapp.sl.books.BooksFactory
import com.example.privatebibleapp.sl.books.BooksModule
import com.example.privatebibleapp.sl.chapters.ChaptersFactory
import com.example.privatebibleapp.sl.chapters.ChaptersModule
import com.example.privatebibleapp.sl.core.CoreModule
import com.example.privatebibleapp.sl.verses.VersesFactory
import com.example.privatebibleapp.sl.verses.VersesModule

class BibleApp : Application() {

//
    private val coreModule = CoreModule()

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun getMainViewModel() =
        coreModule.getViewModel()
    fun chaptersFactory() = ChaptersFactory(ChaptersModule(coreModule))
    fun booksFactory() = BooksFactory(BooksModule(coreModule))
    fun versesFactory() = VersesFactory(VersesModule(coreModule))
}