package com.example.repository.datasource

import com.example.model.dto.SearchResultDto

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(data: SearchResultDto)
    suspend fun getWord(word: String): List<SearchResultDto>
}