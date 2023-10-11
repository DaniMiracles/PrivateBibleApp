package com.example.privatebibleapp.presenter.books

import com.example.privatebibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    abstract fun cache(uiDataCache: UiDataCache): BooksUi
    class Base(private val booksUi: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) =
            mapper.map(booksUi)
        override fun cache(uiDataCache: UiDataCache) = uiDataCache.cache(booksUi)
    }


}