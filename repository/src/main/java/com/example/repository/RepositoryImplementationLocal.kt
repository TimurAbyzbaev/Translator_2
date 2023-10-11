package com.example.repository

import com.example.model.dto.SearchResultDto
import com.example.repository.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {
    override suspend fun saveToDB(data: SearchResultDto) {
        dataSource.saveToDB(data)
    }

    override suspend fun getWord(word: String): List<SearchResultDto> {
        return dataSource.getWord(word)
    }

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}