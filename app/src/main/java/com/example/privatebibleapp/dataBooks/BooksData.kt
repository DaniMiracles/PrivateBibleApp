package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domainBooks.BooksDomain

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {

    class Success(private val booksData: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(booksData)
    }

    class Fail(private val exception: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(exception)
    }
}