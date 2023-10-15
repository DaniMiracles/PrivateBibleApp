package com.example.privatebibleapp.presenter.verses

import android.view.View
import android.view.ViewGroup
import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.BaseAdapter
import com.example.privatebibleapp.core.BaseViewHolder
import com.example.privatebibleapp.core.CustomTextView
import com.example.privatebibleapp.core.Retry

class VersesAdapter(
    private val retry: Retry
) : BaseAdapter<VerseUi, BaseViewHolder<VerseUi>>() {

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is VerseUi.Base -> 0
        is VerseUi.Fail -> 1
        is VerseUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VerseUi> =
        when (viewType) {
            0 -> VersesViewHolder.Base(R.layout.text_layout.makeView(parent))
            1 -> BaseViewHolder.Fail(R.layout.books_require_error.makeView(parent), retry)
            else -> BaseViewHolder.FullscreenProgress(R.layout.progress.makeView(parent))

        }

    abstract class VersesViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {

        class Base(view: View) : VersesViewHolder(view) {
            private val textView: CustomTextView = view.findViewById(R.id.textView)
            override fun bind(item: VerseUi) = item.map(textView)
        }

    }

}