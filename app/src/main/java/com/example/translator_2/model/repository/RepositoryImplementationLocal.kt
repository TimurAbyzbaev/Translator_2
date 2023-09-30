package com.example.translator_2.model.repository

import com.example.translator_2.model.Data_source.DataSourceLocal
import com.example.translator_2.model.data.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(data: DataModel) {
        dataSource.saveToDB(data)
    }

    override suspend fun getWord(word: String): List<DataModel> {
        return dataSource.getWord(word)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}