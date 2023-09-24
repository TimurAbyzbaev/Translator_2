package com.example.translator_2.Interactors

import io.reactivex.Observable

interface Interactor<T> {
    //Use Case: получение данных для вывода на экран
    //Используем RxJava
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}