package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.core.Abstract

interface BookDomainToUiMapper : Abstract.Mapper {

    fun map(id : Int,name:String) : BookUi

    class Base() : BookDomainToUiMapper{
        override fun map(id: Int, name: String): BookUi = BookUi.Base(id,name)

    }
}