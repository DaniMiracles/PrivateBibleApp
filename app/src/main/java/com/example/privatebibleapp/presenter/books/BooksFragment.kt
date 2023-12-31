package com.example.privatebibleapp.presenter.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.privatebibleapp.R
import com.example.privatebibleapp.presenter.BaseFragment
import com.example.privatebibleapp.presenter.CollapseListener
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.core.Retry

class BooksFragment : BaseFragment() {

    private val booksViewModelFactory by lazy {
        (requireActivity().application as BibleApp).booksFactory()
    }

    private val booksViewModel by activityViewModels<BooksViewModel> {booksViewModelFactory}

    override fun getTitle(): String = getString(R.string.app_name)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val booksAdapter = BibleAdapter(object : Retry {
            override fun tryAgain() = booksViewModel.fetchBooks()
        },
            object : CollapseListener {
                override fun collapseOrExpand(id: Int) = booksViewModel.collapseOrExpand(id)
            },
            object : BibleAdapter.BookListener {
                override fun showBook(id: Int, name: String) = booksViewModel.showBook(id, name)
            })

        recyclerView?.adapter = booksAdapter

        booksViewModel.observe(this, Observer { listBookUi ->
            booksAdapter.update(listBookUi)
        })

        booksViewModel.init()
    }


    override fun onPause() {
        super.onPause()
        booksViewModel.saveCollapsedStates()
    }
}