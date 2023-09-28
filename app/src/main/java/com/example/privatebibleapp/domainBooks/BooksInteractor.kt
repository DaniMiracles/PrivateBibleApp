package com.example.privatebibleapp.domainBooks

import com.example.privatebibleapp.dataBooks.BookRepository
import com.example.privatebibleapp.dataBooks.BooksDataToDomainMapper

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val bookRepository: BookRepository,
        private val booksDataToDomainMapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            val booksData = bookRepository.fetchBooks()
            return booksData.map(booksDataToDomainMapper)
        }
    }
}