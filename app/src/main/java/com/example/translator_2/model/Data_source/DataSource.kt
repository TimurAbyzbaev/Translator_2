package com.example.translator_2.model.Data_source

interface DataSource<T> {
    suspend fun getData(word: String): T
}