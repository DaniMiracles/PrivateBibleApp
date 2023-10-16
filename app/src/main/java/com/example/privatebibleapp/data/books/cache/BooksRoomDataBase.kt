package com.example.privatebibleapp.data.books.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.privatebibleapp.data.chapters.cache.ChapterDb
import com.example.privatebibleapp.data.chapters.cache.ChaptersDao

@Database(entities = [BookDb::class,ChapterDb::class], version = 10, exportSchema = false)
abstract class BooksRoomDataBase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    abstract fun chaptersDao() : ChaptersDao


}