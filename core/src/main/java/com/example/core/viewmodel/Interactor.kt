package com.example.core.viewmodel

interface Interactor<T> {
    //Use Case: получение данных для вывода на экран
    //Используем RxJava
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}