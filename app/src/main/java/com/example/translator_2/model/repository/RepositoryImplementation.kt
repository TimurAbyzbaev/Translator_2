package com.example.translator_2.model.repository

import com.example.translator_2.model.Data_source.DataSource
import com.example.translator_2.model.data.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}