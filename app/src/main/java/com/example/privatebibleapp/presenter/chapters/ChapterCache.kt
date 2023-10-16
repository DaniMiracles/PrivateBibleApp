package com.example.privatebibleapp.presenter.chapters

import android.content.Context
import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.core.Save

interface ChapterCache : Save<Int>, Read<Int> {

    class Base(context: Context) : ChapterCache {

        private val sharedPreferences =
            context.getSharedPreferences(CHAPTER_ID_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Int) = sharedPreferences.edit().putInt(CHAPTER_ID_KEY, data).apply()

        override fun read(): Int = sharedPreferences.getInt(CHAPTER_ID_KEY, 0)


        private companion object {
            const val CHAPTER_ID_FILENAME = "chapterIdFileName"
            const val CHAPTER_ID_KEY = "chapterIdKey"
        }

    }
}