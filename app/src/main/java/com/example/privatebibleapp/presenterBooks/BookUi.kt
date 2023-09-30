package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    abstract class Info(private val id: Int, private val name: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    object Progress : BookUi()

    class Base(id: Int, name: String) : Info(id, name)
    class Testament(id: Int, name: String) : Info(id, name)

    class Fail(private val errorType: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(errorType)
    }
}

interface StringMapper : Abstract.Mapper {
    fun map(text: String)
}