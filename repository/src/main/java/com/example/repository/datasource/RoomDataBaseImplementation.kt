package com.example.repository.datasource

import com.example.model.AppState
import com.example.model.dto.SearchResultDto
import com.example.repository.convertDataModelSuccessToEntity
import com.example.repository.mapHistoryEntityToSearchResult
import com.example.repository.mapSearchResultToResult
import com.example.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {
    override suspend fun saveToDB(data: SearchResultDto) {
        convertDataModelSuccessToEntity(AppState.Success(mapSearchResultToResult(listOf(data))))?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getWord(word: String): List<SearchResultDto> {
        val entityArray = mutableListOf<com.example.repository.room.HistoryEntity>()
        entityArray.add(historyDao.getDataByWord(word))
        return mapHistoryEntityToSearchResult(entityArray)
    }

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}