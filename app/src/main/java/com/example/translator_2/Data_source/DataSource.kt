package com.example.translator_2.Data_source

import io.reactivex.Observable

interface DataSource<T> {
    suspend fun getData(word: String): T
}