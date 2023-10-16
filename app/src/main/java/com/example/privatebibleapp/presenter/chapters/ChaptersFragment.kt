package com.example.privatebibleapp.presenter.chapters

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.privatebibleapp.presenter.BaseFragment
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.core.Retry

class ChaptersFragment : BaseFragment() {

    private lateinit var chaptersViewModel: ChaptersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chaptersViewModel = (requireActivity().application as BibleApp).chaptersViewModel
    }

    override fun getTitle(): String = chaptersViewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chaptersAdapter = ChaptersAdapter(object : Retry{
            override fun tryAgain() = chaptersViewModel.fetchChapters()
        })

        chaptersViewModel.observeChapters(this, Observer {
            chaptersAdapter.update(it)
        })
        recyclerView?.adapter = chaptersAdapter

        chaptersViewModel.init()
    }
}