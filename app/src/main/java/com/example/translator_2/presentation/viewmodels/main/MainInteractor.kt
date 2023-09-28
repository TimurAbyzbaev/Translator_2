package com.example.translator_2.presentation.viewmodels.main

import com.example.translator_2.model.AppState
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.model.repository.Repository
import com.example.translator_2.model.repository.RepositoryLocal
import com.example.translator_2.presentation.viewmodels.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}