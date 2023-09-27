package com.example.translator_2.model.repository

import com.example.translator_2.model.AppState
import com.example.translator_2.model.Data_source.DataSourceLocal
import com.example.translator_2.model.data.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}