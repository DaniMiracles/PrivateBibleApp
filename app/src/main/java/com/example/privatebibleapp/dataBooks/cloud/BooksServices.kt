package com.example.privatebibleapp.dataBooks.cloud

import retrofit2.http.GET

interface BooksServices {

    @GET("books")
    fun fetchBooks() : List<BookCloud>
}