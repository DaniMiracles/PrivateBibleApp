package com.example.privatebibleapp.presenter.chapters

import android.view.View
import android.view.ViewGroup
import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.BaseAdapter
import com.example.privatebibleapp.core.BaseViewHolder
import com.example.privatebibleapp.core.CustomTextView
import com.example.privatebibleapp.core.Retry

class ChaptersAdapter(private val retry: Retry) :
    BaseAdapter<ChapterUi, BaseViewHolder<ChapterUi>>() {

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is ChapterUi.Base -> 0
        is ChapterUi.Fail -> 1
        is ChapterUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChapterUi> =
        when (viewType) {
            0 -> ChaptersViewHolder.Base(R.layout.text_layout.makeView(parent))
            1 -> BaseViewHolder.Fail(R.layout.books_require_error.makeView(parent), retry)
            else -> BaseViewHolder.FullscreenProgress(R.layout.progress.makeView(parent))
        }

    abstract class ChaptersViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

        class Base(view: View) : ChaptersViewHolder(view) {
            private val textView : CustomTextView = view.findViewById(R.id.textView)
            override fun bind(item: ChapterUi) = item.map(textView)
        }
    }

}