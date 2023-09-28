package com.example.privatebibleapp.dataBooks.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BooksServices {

    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}