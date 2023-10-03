package com.example.privatebibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.privatebibleapp.core.BibleApp

class MainActivity : AppCompatActivity() {

    private lateinit var booksViewModel : BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val booksRecyclerView = findViewById<RecyclerView>(R.id.booksRecyclerView)
        booksViewModel = (application as BibleApp).booksViewModel
        val dividerItemDecoration = DividerItemDecoration(this,RecyclerView.VERTICAL)

        val booksAdapter = BibleAdapter(object : BibleViewHolder.Retry {
            override fun tryAgain() {
                booksViewModel.fetchBooks()
            }
        },
            object : CollapseListener{
                override fun collapseOrExpand(id: Int) {
                    booksViewModel.collapseOrExpand(id)
                }

            })

        booksRecyclerView.adapter = booksAdapter
        booksRecyclerView.addItemDecoration(dividerItemDecoration)

        booksViewModel.observe(this, Observer { listBookUi ->
            booksAdapter.update(listBookUi) })

        booksViewModel.fetchBooks()

    }

    override fun onPause() {
        super.onPause()
        booksViewModel.saveCollapsedStates()
    }
}