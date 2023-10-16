package com.example.privatebibleapp.data.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.books.BookDomain
import com.example.privatebibleapp.domain.books.BooksDomain
import com.example.privatebibleapp.domain.books.TypeTestament

abstract class BooksDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>() {

    class Base(private val bookDataToDomainMapper: BookDataToDomainMapper) :
        BooksDataToDomainMapper() {

        override fun map(data: List<BookData>): BooksDomain {
            val booksDomain: MutableList<BookDomain> = mutableListOf()
            var testament = ""

            data.forEach { bookData ->
                if (bookData.compare(testament)) {
                    booksDomain.add(bookData.map(bookDataToDomainMapper))
                } else {
                    if (testament.isEmpty())
                        booksDomain.add(BookDomain.Testament(TypeTestament.OLD))
                    else
                        booksDomain.add(BookDomain.Testament(TypeTestament.NEW))
                    booksDomain.add(bookData.map(bookDataToDomainMapper))
                    testament = bookData.saveTestament()
                }
            }
            return BooksDomain.Success(booksDomain.toList())
        }

        override fun map(e: Exception): BooksDomain = BooksDomain.Fail(errorType(e))
    }
}