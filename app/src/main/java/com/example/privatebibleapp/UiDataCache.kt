package com.example.privatebibleapp

import com.example.privatebibleapp.presenterBooks.BookUi

interface UiDataCache {

    fun cache(list: List<BookUi>)
    fun changeState(id : Int) : List<BookUi>


    class Base() : UiDataCache{
        private val cachedList = ArrayList<BookUi>()


        override fun cache(list: List<BookUi>)  {
            cachedList.clear()
            cachedList.addAll(list)
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


    }
}