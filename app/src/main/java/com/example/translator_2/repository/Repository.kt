package com.example.translator_2.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}