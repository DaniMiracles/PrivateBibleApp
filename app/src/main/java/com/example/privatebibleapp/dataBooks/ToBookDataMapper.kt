package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract

interface ToBookDataMapper : Abstract.Mapper {
    fun map(id : Int,name : String) : BookData

    class Base : ToBookDataMapper{
        override fun map(id: Int, name: String): BookData = BookData(id,name)
    }
}