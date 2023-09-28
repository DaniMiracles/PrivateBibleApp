package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.domainBooks.BookDomain
import com.example.privatebibleapp.domainBooks.ErrorType

interface BooksDomainToUiMapper : Abstract.Mapper {

    fun map(listBookDomain: List<BookDomain>): BooksUi
    fun map(errorType: ErrorType): BooksUi

    class Base(
        private val manageResources: ManageResources,
        private val bookDomainToUiMapper: BookDomainToUiMapper
    ) : BooksDomainToUiMapper {

        override fun map(listBookDomain: List<BookDomain>): BooksUi =
            BooksUi.Success(listBookDomain, bookDomainToUiMapper)


        override fun map(errorType: ErrorType): BooksUi = BooksUi.Fail(errorType, manageResources)
    }
}