package com.example.privatebibleapp.presenter.verses

import com.example.privatebibleapp.core.Communication

interface VersesCommunication : Communication<List<VerseUi>> {

    class Base() : Communication.Base<List<VerseUi>>(),VersesCommunication
}