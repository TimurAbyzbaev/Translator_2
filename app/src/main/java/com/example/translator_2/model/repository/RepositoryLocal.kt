package com.example.translator_2.model.repository

import com.example.translator_2.model.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}