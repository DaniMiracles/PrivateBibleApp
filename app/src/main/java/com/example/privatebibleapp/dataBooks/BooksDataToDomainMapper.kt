package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domainBooks.BookDataToDomainMapper
import com.example.privatebibleapp.domainBooks.BookDomain
import com.example.privatebibleapp.domainBooks.BooksDomain
import com.example.privatebibleapp.domainBooks.ErrorType
import com.example.privatebibleapp.domainBooks.TypeTestament
import retrofit2.HttpException
import java.net.UnknownHostException

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(listBookData: List<BookData>): BooksDomain
    fun map(exception: Exception): BooksDomain

    class Base(private val bookDataToDomainMapper: BookDataToDomainMapper) :
        BooksDataToDomainMapper {

        override fun map(listBookData: List<BookData>): BooksDomain {
            val booksDomain: MutableList<BookDomain> = mutableListOf()
            var testament = ""

            listBookData.forEach { bookData ->
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

        override fun map(exception: Exception): BooksDomain = BooksDomain.Fail(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}