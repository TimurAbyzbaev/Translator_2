package com.example.translator_2.model.repository

import com.example.translator_2.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(data: DataModel)
    suspend fun getWord(word: String): List<DataModel>
}