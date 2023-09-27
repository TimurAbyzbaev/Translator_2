package com.example.translator_2.model.repository

import io.reactivex.Observable

interface Repository<T> {
    suspend fun getData(word: String): T
}