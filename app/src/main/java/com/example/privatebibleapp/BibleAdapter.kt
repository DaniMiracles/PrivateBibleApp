package com.example.privatebibleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.privatebibleapp.presenterBooks.BookUi
import com.example.privatebibleapp.presenterBooks.StringMapper

class BibleAdapter(private val retry: BibleViewHolder.Retry) :
    RecyclerView.Adapter<BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    override fun getItemViewType(position: Int): Int = when (books[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Progress -> 2
    }

    fun update(newBooks: List<BookUi>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        val viewHolder = when (viewType) {
            0 -> BibleViewHolder.Base(
                LayoutInflater.from(parent.context).inflate(R.layout.bible_books, parent,false)
            )
            1 -> BibleViewHolder.Fail(
                LayoutInflater.from(parent.context).inflate(R.layout.books_require_error, parent,false),
                retry
            )
            else -> BibleViewHolder.FullscreenProgress(
                LayoutInflater.from(parent.context).inflate(R.layout.progress, parent,false)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size
}

abstract class BibleViewHolder(view: View) :RecyclerView.ViewHolder(view) {

    open fun bind(bookUi: BookUi) {}

    class FullscreenProgress(view: View) : BibleViewHolder(view)

    class Base(view: View) : BibleViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.nameBook)
        override fun bind(bookUi: BookUi) {
            bookUi.map(object : StringMapper {
                override fun map(text: String) {
                    name.text = text
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

