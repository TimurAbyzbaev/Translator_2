package com.example.translator_2.model.Data_source

import com.example.translator_2.model.AppState

interface DataSourceLocal<T>: DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}