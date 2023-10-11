package com.example.privatebibleapp.presenter.chapters

import com.example.privatebibleapp.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>> {

    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}