package com.example.repository

import com.example.model.data.DataModel
import com.example.repository.datasource.DataSourceLocal

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