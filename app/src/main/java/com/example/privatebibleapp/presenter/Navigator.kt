package com.example.privatebibleapp.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.privatebibleapp.presenter.books.BooksNavigator
import com.example.privatebibleapp.presenter.chapters.ChaptersNavigator
import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.core.Save
import com.example.privatebibleapp.presenter.books.BooksFragment
import com.example.privatebibleapp.presenter.chapters.ChaptersFragment


interface Navigator : Read<Int>, Save<Int>, MainNavigator<Fragment>, BooksNavigator,
    ChaptersNavigator {

    class Base(context: Context) : Navigator {

        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun read(): Int =
            sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)

        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        }

        override fun saveBooksScreen() {
            save(BOOKS_SCREEN)
        }

        override fun nextScreen(communication: NavigationCommunication) {
            communication.map(read() + 1)
        }

        override fun saveChaptersScreen() {
            save(CHAPTERS_SCREEN)
        }

        override fun getFragment(id:Int): Fragment {
            val fragment = when (id) {
                BOOKS_SCREEN -> BooksFragment()
                CHAPTERS_SCREEN -> ChaptersFragment()
                else -> {
                    throw IllegalStateException("screen id undefined $id")
                }
            }
            return fragment
        }

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"

            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
        }

    }
}

