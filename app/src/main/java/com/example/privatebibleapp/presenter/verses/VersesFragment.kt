package com.example.privatebibleapp.presenter.verses

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.core.Retry
import com.example.privatebibleapp.presenter.BaseFragment

class VersesFragment : BaseFragment() {

    private lateinit var versesViewModel: VersesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        versesViewModel = (requireActivity().application as BibleApp).versesViewModel
    }

    override fun getTitle(): String = versesViewModel.getTitle()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val versesAdapter = VersesAdapter(object : Retry {
            override fun tryAgain() = versesViewModel.fetchVerses()
        })

        versesViewModel.observeVerses(this, Observer { listVerseUi ->
            versesAdapter.update(listVerseUi)
        })
        recyclerView?.adapter = versesAdapter

        versesViewModel.init()
    }
}