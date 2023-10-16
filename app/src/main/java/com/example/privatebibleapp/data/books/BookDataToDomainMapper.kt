package com.example.privatebibleapp.data.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.books.BookDomain

interface BookDataToDomainMapper  : Abstract.Mapper {

    fun map(id:Int,name : String,testament:String) : BookDomain

    class Base() : BookDataToDomainMapper {
        override fun map(id: Int, name: String,testament:String): BookDomain =
            BookDomain.Base(id, name)
    }
}