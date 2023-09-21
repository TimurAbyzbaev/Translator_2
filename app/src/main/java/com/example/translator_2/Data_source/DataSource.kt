package com.example.translator_2.Data_source

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}