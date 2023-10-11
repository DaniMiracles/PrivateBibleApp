package com.example.privatebibleapp.data.books.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BooksServices {

    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}