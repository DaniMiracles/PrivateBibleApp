package com.example.privatebibleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.privatebibleapp.presenterBooks.BookUi
import com.example.privatebibleapp.presenterBooks.CollapseMapper
import com.example.privatebibleapp.presenterBooks.StringMapper

class BibleAdapter(
    private val retry: BibleViewHolder.Retry,
    private val collapseListener: CollapseListener
) :
    RecyclerView.Adapter<BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    override fun getItemViewType(position: Int): Int = when (books[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    fun update(newBooks: List<BookUi>) {
        val diffUtilCallback = DiffUtilCallback(books, newBooks)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        books.clear()
        books.addAll(newBooks)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder =
        when (viewType) {
            0 -> BibleViewHolder.Base(R.layout.bible_books.makeView(parent))
            1 -> BibleViewHolder.Fail(R.layout.books_require_error.makeView(parent), retry)
            2 -> BibleViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
            else -> BibleViewHolder.FullscreenProgress(R.layout.progress.makeView(parent))
        }


    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size
}

abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(bookUi: BookUi) {}

    class FullscreenProgress(view: View) : BibleViewHolder(view)

    abstract class Info(view: View) : BibleViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.nameBook)
        override fun bind(bookUi: BookUi) {
            bookUi.map(object : StringMapper {
                override fun map(text: String) {
                    name.text = text
                }
            })
        }
    }

    class Base(view: View) : Info(view)

    class Testament(view: View, private val collapseListener: CollapseListener) : Info(view) {
        override fun bind(bookUi: BookUi) {
            super.bind(bookUi)
            val collapseView = itemView.findViewById<ImageView>(R.id.collapseView)

            itemView.setOnClickListener {
                bookUi.collapseOrExpand(collapseListener)
            }
            bookUi.showCollapsed(object : CollapseMapper {
                override fun show(collapsed: Boolean) {
                    val iconId = if (collapsed) {
                        R.drawable.expand_more_px
                    } else {
                        R.drawable.expand_less_px
                    }
                    collapseView.setImageResource(iconId)
                }

            })
        }
    }

    class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {
        private val errorMessage = view.findViewById<TextView>(R.id.booksErrorTV)
        private val retryButton = view.findViewById<Button>(R.id.retryButton)
        override fun bind(bookUi: BookUi) {
            bookUi.map(object : StringMapper {
                override fun map(text: String) {
                    errorMessage.text = text
                }
            })
            retryButton.setOnClickListener {
                retry.tryAgain()
            }
        }
    }

    interface Retry {
        fun tryAgain()
    }
}

private fun Int.makeView(parent: ViewGroup): View =
    LayoutInflater.from(parent.context).inflate(this, parent, false)
