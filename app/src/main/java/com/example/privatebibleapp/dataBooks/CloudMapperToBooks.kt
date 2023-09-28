package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.dataBooks.cloud.BookCloud

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