package com.example.privatebibleapp.presenter.chapters

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import com.example.privatebibleapp.presenter.BaseFragment
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.core.Retry

class ChaptersFragment : BaseFragment() {

    private val chaptersViewModelFactory by lazy {
        (requireActivity().application as BibleApp).chaptersFactory()
    }

    private val chaptersViewModel by viewModels<ChaptersViewModel>  { chaptersViewModelFactory }


    override fun getTitle(): String = chaptersViewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chaptersAdapter = ChaptersAdapter(object : Retry{
            override fun tryAgain() = chaptersViewModel.fetchChapters()
        },
            object : ChaptersAdapter.ChapterClickListener{
                override fun show(item: ChapterUi) = item.open(chaptersViewModel)

            })

        chaptersViewModel.observeChapters(this, Observer {
            chaptersAdapter.update(it)
        })
        recyclerView?.adapter = chaptersAdapter

        chaptersViewModel.init()
    }
}