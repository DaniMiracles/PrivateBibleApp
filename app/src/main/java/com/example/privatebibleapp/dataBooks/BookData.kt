package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domainBooks.BookDataToDomainMapper
import com.example.privatebibleapp.domainBooks.BookDomain

class BookData(private val id: Int,private val name: String) :Abstract.Object<BookDomain,BookDataToDomainMapper>{


    override fun map(mapper: BookDataToDomainMapper): BookDomain = mapper.map(id,name)

}