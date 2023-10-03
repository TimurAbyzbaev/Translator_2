package com.example.translator_2.presentation.viewmodels.main

import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.core.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: com.example.repository.Repository<List<DataModel>>,
    private val localRepository: com.example.repository.RepositoryLocal<List<DataModel>>,
) : com.example.core.viewmodel.Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))

        } else {
            appState = AppState.Success(localRepository.getWord(word))
        }
        return appState
    }

    suspend fun saveInDB(data: com.example.model.data.DataModel) {
        localRepository.saveToDB(data)
    }
}