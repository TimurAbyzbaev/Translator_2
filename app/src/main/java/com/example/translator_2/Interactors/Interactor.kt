package com.example.translator_2.Interactors

import io.reactivex.Observable

interface Interactor<T> {
    //Use Case: получение данных для вывода на экран
    //Используем RxJava
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}