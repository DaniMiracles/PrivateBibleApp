package com.example.privatebibleapp.presenter.books


import com.example.privatebibleapp.core.Communication

interface BooksCommunication : Communication<List<BookUi>> {

    class Base() : Communication.Base<List<BookUi>>(), BooksCommunication

}