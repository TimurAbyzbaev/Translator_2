package com.example.translator_2.model.Data_source

import com.example.translator_2.model.data.DataModel
import com.example.translator_2.model.room.HistoryDao
import com.example.translator_2.model.room.HistoryEntity
import com.example.translator_2.utils.convertDataModelSuccessToEntity
import com.example.translator_2.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(data: DataModel) {
        convertDataModelSuccessToEntity(data)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getWord(word: String): List<DataModel> {
        val entityArray = mutableListOf<HistoryEntity>()
        entityArray.add(historyDao.getDataByWord(word))
        return mapHistoryEntityToSearchResult(entityArray)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

}