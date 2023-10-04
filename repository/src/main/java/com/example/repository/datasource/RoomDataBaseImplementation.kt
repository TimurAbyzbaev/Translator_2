package com.example.repository.datasource

import com.example.model.data.DataModel
import com.example.repository.convertDataModelSuccessToEntity
import com.example.repository.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: com.example.repository.room.HistoryDao) :
    DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(data: DataModel) {
        convertDataModelSuccessToEntity(data)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getWord(word: String): List<DataModel> {
        val entityArray = mutableListOf<com.example.repository.room.HistoryEntity>()
        entityArray.add(historyDao.getDataByWord(word))
        return mapHistoryEntityToSearchResult(entityArray)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

}