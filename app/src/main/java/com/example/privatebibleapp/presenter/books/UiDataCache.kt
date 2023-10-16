package com.example.privatebibleapp.presenter.books

import com.example.privatebibleapp.presenter.IdCache

interface UiDataCache {

    fun cache(list: List<BookUi>) : BooksUi
    fun changeState(id: Int): List<BookUi>
    fun saveState()

    class Base(private val cacheId: IdCache) : UiDataCache {
        private val cachedList = ArrayList<BookUi>()


        override fun cache(list: List<BookUi>) : BooksUi {
            cachedList.clear()
            cachedList.addAll(list)
            var newList: List<BookUi> = ArrayList(list)
            val ids = cacheId.read()
            ids.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newList = ArrayList<BookUi>()
            val item = cachedList.find { bookUi ->
                bookUi.matches(id)
            }

            var add = false

            cachedList.forEachIndexed { index, bookUi ->
                if (bookUi is BookUi.Testament) {
                    if (item == bookUi) {
                        val element = bookUi.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(bookUi)
                        add = !bookUi.isCollapsed()
                    }
                } else {
                    if (add) newList.add(bookUi)
                }
            }
            return newList
        }

        override fun saveState() {
            cacheId.start()
            cachedList.filter {bookUi ->
                bookUi.isCollapsed()
            }.forEach {bookUi ->
                bookUi.saveId(cacheId)
            }
            cacheId.finish()
        }


    }
}