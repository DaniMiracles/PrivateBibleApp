package com.example.privatebibleapp.core

import com.example.privatebibleapp.presenter.NavigationCommunication

interface NavigateForward {
    fun nextScreen(communication: NavigationCommunication)
}