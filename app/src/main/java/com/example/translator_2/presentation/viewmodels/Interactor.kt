package com.example.translator_2.presentation.viewmodels

interface Interactor<T> {
    //Use Case: получение данных для вывода на экран
    //Используем RxJava
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}