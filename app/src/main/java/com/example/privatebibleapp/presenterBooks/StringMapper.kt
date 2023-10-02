package com.example.privatebibleapp.presenterBooks

import com.example.privatebibleapp.core.Abstract

interface StringMapper : Abstract.Mapper {
    fun map(text: String)
}