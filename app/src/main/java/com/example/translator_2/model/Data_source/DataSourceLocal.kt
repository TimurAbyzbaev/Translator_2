package com.example.translator_2.model.Data_source

import com.example.translator_2.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(data: DataModel)
    suspend fun getWord(word: String): List<DataModel>
}