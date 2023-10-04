package com.example.history.viewmodel

import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.repository.Repository
import com.example.repository.RepositoryLocal

class HistoryInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>,
) : com.example.core.viewmodel.Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}