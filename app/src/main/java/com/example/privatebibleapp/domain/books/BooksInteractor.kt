package com.example.privatebibleapp.domain.books

import com.example.privatebibleapp.data.books.BookRepository
import com.example.privatebibleapp.data.books.BooksDataToDomainMapper

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