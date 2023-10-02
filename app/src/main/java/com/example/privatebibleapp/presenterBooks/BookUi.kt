package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.CollapseListener
import com.example.privatebibleapp.Collapsing
import com.example.privatebibleapp.Matcher
import com.example.privatebibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, StringMapper>, Matcher<Int>,Collapsing {

    override fun map(mapper: StringMapper) = Unit

    override fun matches(arg: Int): Boolean = false

    open fun changeState() : BookUi = Empty

    object Empty : BookUi()

    abstract class Info(protected val id: Int, protected val name: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int): Boolean = arg == id
    }

    object Progress : BookUi()

    class Base(id: Int, name: String) : Info(id, name)
    class Testament(id: Int, name: String, private val collapsed: Boolean = false) :
        Info(id, name) {
        override fun collapseOrExpand(listener: CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) =
            mapper.show(collapsed)
        override fun changeState() = Testament(id, name, !collapsed)
        override fun isCollapsed(): Boolean = collapsed

    }

    class Fail(private val errorType: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(errorType)
    }
}

