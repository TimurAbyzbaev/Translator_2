package com.example.repository

import com.example.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(data: DataModel)
    suspend fun getWord(word: String): List<DataModel>
}