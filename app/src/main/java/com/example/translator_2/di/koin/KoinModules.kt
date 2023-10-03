package com.example.translator_2.di.koin

import androidx.room.Room
import com.example.model.data.DataModel
import com.example.repository.room.HistoryDataBase
import com.example.history.viewmodel.HistoryInteractor
import com.example.history.viewmodel.HistoryViewModel
import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<com.example.repository.Repository<List<DataModel>>> {
        com.example.repository.RepositoryImplementation(
            com.example.repository.datasource.RetrofitImplementation()
        )
    }
    single<com.example.repository.RepositoryLocal<List<DataModel>>> {
        com.example.repository.RepositoryImplementationLocal(
            com.example.repository.datasource.RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}