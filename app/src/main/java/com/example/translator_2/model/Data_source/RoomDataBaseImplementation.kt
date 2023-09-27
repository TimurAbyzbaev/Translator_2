package com.example.translator_2.model.Data_source

import com.example.translator_2.model.AppState
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.model.room.HistoryDao
import com.example.translator_2.utils.convertDataModelSuccessToEntity
import com.example.translator_2.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao): DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

}