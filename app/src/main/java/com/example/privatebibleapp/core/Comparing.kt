package com.example.privatebibleapp.core



interface Comparing<T : Comparing<T>> {
    fun sameContent(item:T) = false
    fun same(item:T) = false
}