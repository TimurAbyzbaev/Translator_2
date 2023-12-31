package com.example.translator_2.di.koin

import androidx.room.Room
import com.example.history.view.HistoryActivity
import com.example.model.dto.SearchResultDto
import com.example.repository.room.HistoryDataBase
import com.example.history.viewmodel.HistoryInteractor
import com.example.history.viewmodel.HistoryViewModel
import com.example.translator_2.presentation.view.main.MainActivity
import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<com.example.repository.Repository<List<SearchResultDto>>> {
        com.example.repository.RepositoryImplementation(
            com.example.repository.datasource.RetrofitImplementation()
        )
    }
    single<com.example.repository.RepositoryLocal<List<SearchResultDto>>> {
        com.example.repository.RepositoryImplementationLocal(
            com.example.repository.datasource.RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()){
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }

}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}