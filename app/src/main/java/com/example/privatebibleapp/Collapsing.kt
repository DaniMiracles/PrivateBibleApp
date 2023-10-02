package com.example.privatebibleapp

import com.example.privatebibleapp.presenterBooks.CollapseMapper

interface Collapsing {
    fun collapseOrExpand(listener: CollapseListener) = Unit
    fun showCollapsed(mapper: CollapseMapper) = Unit
    fun isCollapsed() = false
}