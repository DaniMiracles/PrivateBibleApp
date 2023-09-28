package com.example.privatebibleapp.dataBooks.cache

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm

    class Base() : RealmProvider {
        override fun provide(): Realm = Realm.getDefaultInstance()
    }
}