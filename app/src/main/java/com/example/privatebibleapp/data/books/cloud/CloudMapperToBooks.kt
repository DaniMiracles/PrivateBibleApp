package com.example.privatebibleapp.data.books.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.books.BookData
import com.example.privatebibleapp.data.books.ToBookDataMapper

interface CloudMapperToBooks : Abstract.Mapper {

    fun map(listBookCloud: List<BookCloud>): List<BookData>

    class Base(private val toBookDataMapper: ToBookDataMapper) : CloudMapperToBooks {
        override fun map(listBookCloud: List<BookCloud>): List<BookData> {
            val listBookData = listBookCloud.map { bookCloud ->
                bookCloud.map(toBookDataMapper)
            }
            return listBookData
        }

    }
}