package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.CollapseListener
import com.example.privatebibleapp.Collapsing
import com.example.privatebibleapp.Comparing
import com.example.privatebibleapp.IdCache
import com.example.privatebibleapp.Matcher
import com.example.privatebibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, StringMapper>, Matcher<Int>, Collapsing, Comparing {

    override fun map(mapper: StringMapper) = Unit

    override fun matches(arg: Int): Boolean = false

    open fun changeState(): BookUi = Empty
    open fun saveId(cacheId: IdCache) = Unit

    object Empty : BookUi()

    abstract class Info(protected val id: Int, protected val name: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int): Boolean = arg == id
    }

    object Progress : BookUi()

    class Base(id: Int, name: String) : Info(id, name) {
        override fun sameContent(bookUi: BookUi): Boolean = if (bookUi is Base) {
            name == bookUi.name
        } else false

        override fun same(bookUi: BookUi): Boolean = bookUi is Base && id == bookUi.id
    }

    class Testament(id: Int, name: String, private val collapsed: Boolean = false) :
        Info(id, name) {

        override fun collapseOrExpand(listener: CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) =
            mapper.show(collapsed)

        override fun changeState() = Testament(id, name, !collapsed)

        override fun isCollapsed(): Boolean = collapsed

        override fun saveId(cacheId: IdCache) = cacheId.save(id)

        override fun sameContent(bookUi: BookUi): Boolean = if (bookUi is Testament) {
            name == bookUi.name && collapsed == bookUi.collapsed
        } else false

        override fun same(bookUi: BookUi): Boolean = bookUi is Testament && id == bookUi.id

    }

    class Fail(private val errorType: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(errorType)
    }
}

