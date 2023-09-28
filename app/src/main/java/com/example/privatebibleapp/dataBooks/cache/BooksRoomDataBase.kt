package com.example.privatebibleapp.dataBooks.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDb::class], version = 1, exportSchema = false)
abstract class BooksRoomDataBase : RoomDatabase() {

    abstract fun booksDao(): BooksDao


}