package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.core.Abstract

interface CollapseMapper : Abstract.Mapper {
    fun show(collapsed: Boolean)
}