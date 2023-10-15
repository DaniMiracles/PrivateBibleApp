package com.example.privatebibleapp.data.books.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.privatebibleapp.data.chapters.cache.ChapterDb
import com.example.privatebibleapp.data.chapters.cache.ChaptersDao
import com.example.privatebibleapp.data.verses.cache.VerseDb
import com.example.privatebibleapp.data.verses.cache.VersesDao

@Database(entities = [BookDb::class, ChapterDb::class,VerseDb::class], version = 11, exportSchema = false)
abstract class BooksRoomDataBase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    abstract fun chaptersDao(): ChaptersDao

    abstract fun versesDao(): VersesDao
}