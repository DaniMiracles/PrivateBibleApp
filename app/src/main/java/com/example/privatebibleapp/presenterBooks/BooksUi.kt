package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.BooksCommunication
import com.example.privatebibleapp.R
import com.example.privatebibleapp.UiDataCache
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.domainBooks.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    class Success(private val booksUi: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(booksUi)
        fun cache(uiDataCache: UiDataCache) = uiDataCache.cache(booksUi)
    }

    class Fail(
        private val errorType: ErrorType,
        private val manageResources: ManageResources
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            val messageType = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                else -> R.string.something_wrong
            }
            val errorString = manageResources.getString(messageType)
            mapper.map(listOf(BookUi.Fail(errorString)))
        }

    }
}