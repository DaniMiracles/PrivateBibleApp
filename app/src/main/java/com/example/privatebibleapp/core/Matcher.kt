package com.example.privatebibleapp.core

interface Matcher<T> {
    fun matches(arg: T): Boolean
}