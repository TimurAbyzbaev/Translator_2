package com.example.repository

import com.example.model.dto.SearchResultDto

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(data: SearchResultDto)
    suspend fun getWord(word: String): List<SearchResultDto>
}