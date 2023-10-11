package com.example.privatebibleapp.data.books.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {
    fun provideDatabase(): BooksRoomDataBase

    class Base(private val context: Context) : CacheModule {

        private val booksDatabase by lazy {
            return@lazy Room.databaseBuilder(
                context,
                BooksRoomDataBase::class.java,
                "books_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }


        override fun provideDatabase(): BooksRoomDataBase = booksDatabase

    }

}