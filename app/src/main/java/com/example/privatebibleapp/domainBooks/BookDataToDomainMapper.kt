package com.example.privatebibleapp.domainBooks

import com.example.privatebibleapp.core.Abstract

interface BookDataToDomainMapper  : Abstract.Mapper {

    fun map(id:Int,name : String,testament:String) : BookDomain

    class Base() : BookDataToDomainMapper{
        override fun map(id: Int, name: String,testament:String): BookDomain =
            BookDomain.Base(id,name)
    }
}