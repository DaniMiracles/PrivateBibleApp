package com.example.privatebibleapp.data.verses.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface VersesServices {

    @GET("books/{bookId}/chapters/{chapterId}")
    suspend fun fetchVerses(
        @Path("bookId") bookId: Int,
        @Path("chapterId") chapterId: Int,
    ) : ResponseBody
}