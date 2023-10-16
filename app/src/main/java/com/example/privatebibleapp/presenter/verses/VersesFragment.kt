package com.example.privatebibleapp.presenter.verses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.privatebibleapp.core.BibleApp
import com.example.privatebibleapp.core.Retry
import com.example.privatebibleapp.presenter.BaseFragment

class VersesFragment : BaseFragment() {

    private val versesViewModelFactory by lazy {
        (requireActivity().application as BibleApp).versesFactory()
    }

    private val versesViewModel by viewModels<VersesViewModel> { versesViewModelFactory }


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