package com.example.privatebibleapp

import com.example.privatebibleapp.presenterBooks.BookUi

interface Comparing {
    fun sameContent(bookUi: BookUi) = false
    fun same(bookUi: BookUi) = false
}