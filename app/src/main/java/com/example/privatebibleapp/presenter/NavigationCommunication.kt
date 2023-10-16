package com.example.privatebibleapp.presenter

import com.example.privatebibleapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base() : Communication.Base<Int>(), NavigationCommunication
}