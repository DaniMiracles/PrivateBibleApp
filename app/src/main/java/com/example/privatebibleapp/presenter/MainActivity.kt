package com.example.privatebibleapp.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.presenter.Screens.Companion.BOOKS_SCREEN
import com.example.privatebibleapp.presenter.Screens.Companion.CHAPTERS_SCREEN
import com.example.privatebibleapp.presenter.books.BooksFragment
import com.example.privatebibleapp.presenter.chapters.ChaptersFragment
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = (application as BibleApp).mainViewModel

        mainViewModel.observe(this, Observer { typeScreen ->
            val fragment = when (typeScreen) {
                BOOKS_SCREEN -> BooksFragment()
                CHAPTERS_SCREEN -> ChaptersFragment()
                else -> throw IllegalStateException("screen id undefined $typeScreen")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        })
        mainViewModel.init()
    }

    override fun onBackPressed() {
        if (mainViewModel.navigateBack())
            super.onBackPressed()
    }
}