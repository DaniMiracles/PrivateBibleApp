package com.example.privatebibleapp.presenter

import com.example.privatebibleapp.core.CollapseMapper

interface Collapsing {
    fun collapseOrExpand(listener: CollapseListener) = Unit
    fun showCollapsed(mapper: CollapseMapper) = Unit
    fun isCollapsed() = false
}