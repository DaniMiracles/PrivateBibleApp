package com.example.privatebibleapp.presenter.books

import com.example.privatebibleapp.presenter.CollapseListener
import com.example.privatebibleapp.presenter.Collapsing
import com.example.privatebibleapp.presenter.IdCache
import com.example.privatebibleapp.core.Matcher
import com.example.privatebibleapp.core.CollapseMapper
import com.example.privatebibleapp.core.ComparableTextMapper
import com.example.privatebibleapp.core.TextMapper

sealed class BookUi : ComparableTextMapper<BookUi>, Matcher<Int>, Collapsing {

    override fun map(mapper: TextMapper) = Unit

    override fun matches(arg: Int): Boolean = false

    open fun changeState(): BookUi = Empty
    open fun saveId(cacheId: IdCache) = Unit
    open fun open(bookListener: BibleAdapter.BookListener) = Unit

    object Empty : BookUi()

    abstract class Info(protected val id: Int, protected val name: String) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(name)
        override fun matches(arg: Int): Boolean = arg == id
    }

    object Progress : BookUi()

    class Base(id: Int, name: String) : Info(id, name) {
        override fun sameContent(bookUi: BookUi): Boolean = if (bookUi is Base) {
            name == bookUi.name
        } else false

        override fun same(bookUi: BookUi): Boolean = bookUi is Base && id == bookUi.id

        override fun open(bookListener: BibleAdapter.BookListener) = bookListener.showBook(id, name)
    }

    class Testament(id: Int, name: String, private val collapsed: Boolean = false) :
        Info(id, name) {

        override fun collapseOrExpand(listener: CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) =
            mapper.map(collapsed)

        override fun changeState() = Testament(id, name, !collapsed)

        override fun isCollapsed(): Boolean = collapsed

        override fun saveId(cacheId: IdCache) = cacheId.save(id)

        override fun sameContent(bookUi: BookUi): Boolean = if (bookUi is Testament) {
            name == bookUi.name && collapsed == bookUi.collapsed
        } else false

        override fun same(bookUi: BookUi): Boolean = bookUi is Testament && id == bookUi.id

    }

    class Fail(private val errorType: String) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(errorType)
    }
}

