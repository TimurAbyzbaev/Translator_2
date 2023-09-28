package com.example.translator_2.di.koin

import androidx.room.Room
import com.example.translator_2.model.Data_source.RetrofitImplementation
import com.example.translator_2.model.Data_source.RoomDataBaseImplementation
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.model.repository.Repository
import com.example.translator_2.model.repository.RepositoryImplementation
import com.example.translator_2.model.repository.RepositoryImplementationLocal
import com.example.translator_2.model.repository.RepositoryLocal
import com.example.translator_2.model.room.HistoryDataBase
import com.example.translator_2.presentation.viewmodels.history.HistoryInteractor
import com.example.translator_2.presentation.viewmodels.history.HistoryViewModel
import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
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