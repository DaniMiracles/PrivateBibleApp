package com.example.privatebibleapp.core

import android.content.SharedPreferences

interface PreferencesProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}