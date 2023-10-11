package com.example.privatebibleapp.data.books.cache

import com.example.privatebibleapp.core.Abstract

interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String,testament:String): BookDb

    class Base() : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String,testament:String): BookDb =
            BookDb(id, name,testament)

    }
}

