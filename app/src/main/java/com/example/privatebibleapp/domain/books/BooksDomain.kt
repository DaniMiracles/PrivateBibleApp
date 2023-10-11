package com.example.privatebibleapp.domain.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.presenter.books.BooksUi

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    class Success(private val booksDomain: List<BookDomain>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(booksDomain)

    }

    class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(errorType)
    }
}
