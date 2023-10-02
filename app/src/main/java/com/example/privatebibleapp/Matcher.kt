package com.example.privatebibleapp

interface Matcher<T> {
    fun matches(arg: T): Boolean
}