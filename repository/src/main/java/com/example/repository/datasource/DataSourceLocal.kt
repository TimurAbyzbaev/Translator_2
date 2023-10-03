package com.example.repository.datasource

import com.example.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(data: DataModel)
    suspend fun getWord(word: String): List<DataModel>
}