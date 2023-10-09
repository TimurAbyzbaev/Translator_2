package com.example.translator_2.presentation.viewmodels.main

import com.example.core.viewmodel.Interactor
import com.example.model.AppState
import com.example.model.dto.SearchResultDto
import com.example.repository.Repository
import com.example.repository.RepositoryLocal
import com.example.repository.mapSearchResultToResult

class MainInteractor(
    private val remoteRepository: Repository<List<SearchResultDto>>,
    private val localRepository: RepositoryLocal<List<SearchResultDto>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(remoteRepository.getData(word)))

        } else {
            appState = AppState.Success(mapSearchResultToResult(localRepository.getWord(word)))
        }
        return appState
    }

    suspend fun saveInDB(data: SearchResultDto) {
        localRepository.saveToDB(data)
    }
}