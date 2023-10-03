package com.example.privatebibleapp

import androidx.recyclerview.widget.DiffUtil
import com.example.privatebibleapp.presenterBooks.BookUi

class DiffUtilCallback(
    private val oldList: List<BookUi>,
    private val newList: List<BookUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])
}