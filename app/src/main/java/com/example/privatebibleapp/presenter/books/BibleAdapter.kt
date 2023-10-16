package com.example.privatebibleapp.presenter.books

import android.view.View
import android.view.ViewGroup
import com.example.privatebibleapp.presenter.CollapseListener
import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.BaseAdapter
import com.example.privatebibleapp.core.BaseViewHolder
import com.example.privatebibleapp.core.CollapseView
import com.example.privatebibleapp.core.CustomTextView
import com.example.privatebibleapp.core.Retry

class BibleAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener,
    private val bookListener: BookListener
) : BaseAdapter<BookUi, BaseViewHolder<BookUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> BooksViewHolder.Base(R.layout.text_layout.makeView(parent), bookListener)
        1 -> BaseViewHolder.Fail(R.layout.books_require_error.makeView(parent), retry)
        2 -> BooksViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        else -> BaseViewHolder.FullscreenProgress(R.layout.progress.makeView(parent))
    }

    abstract class BooksViewHolder(view: View) : BaseViewHolder<BookUi>(view) {

        abstract class Info(view: View) : BooksViewHolder(view) {
            protected val name: CustomTextView = itemView.findViewById(R.id.textView)
            override fun bind(item: BookUi) = item.map(name)
        }

        class Base(view: View, private val bookListener: BookListener) : Info(view) {
            override fun bind(item: BookUi) {
                super.bind(item)
                name.setOnClickListener {
                    item.open(bookListener)
                }
            }
        }

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<CollapseView>(R.id.collapseView)
            override fun bind(bookUi: BookUi) {
                super.bind(bookUi)
                itemView.setOnClickListener {
                    bookUi.collapseOrExpand(collapse)
                }
                bookUi.showCollapsed(collapseView)
            }
        }
    }


    interface BookListener {
        fun showBook(id: Int, name: String)
    }
}