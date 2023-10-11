package com.example.privatebibleapp.domain.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.presenter.books.BookUi
import com.example.privatebibleapp.presenter.books.BooksUi

abstract class BooksDomainToUiMapper(manageResources: ManageResources) :
    Abstract.Mapper.DomainToUi.Base<List<BookDomain>, BooksUi>(manageResources) {

    class Base(
        manageResources: ManageResources,
        private val bookDomainToUiMapper: BookDomainToUiMapper
    ) : BooksDomainToUiMapper(manageResources) {

        override fun map(data: List<BookDomain>): BooksUi = BooksUi.Base(data.map { bookDomain ->
            bookDomain.map(bookDomainToUiMapper)
        })

        override fun map(errorType: ErrorType): BooksUi =
            BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))

    }
}