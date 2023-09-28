package com.example.privatebibleapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.presenterBooks.BookUi

interface BooksCommunication : Abstract.Mapper {

    fun map(listBookUi: List<BookUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>)

    class Base() : BooksCommunication {
        private val listLiveData = MutableLiveData<List<BookUi>>()
        override fun map(listBookUi: List<BookUi>) {
          listLiveData.value = listBookUi
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
            listLiveData.observe(owner, observer)
        }

    }

}