package com.example.privatebibleapp.core

interface Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }


    interface Mapper {
        class Empty
    }
}