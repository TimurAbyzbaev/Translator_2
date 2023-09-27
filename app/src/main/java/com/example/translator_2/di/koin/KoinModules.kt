package com.example.translator_2.di.koin

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import com.example.translator_2.model.repository.Repository
import com.example.translator_2.model.repository.RepositoryImplementation
import com.example.translator_2.model.Data_source.RetrofitImplementation
import com.example.translator_2.model.Data_source.RoomDataBaseImplementation
import com.example.translator_2.model.repository.RepositoryImplementationLocal
import com.example.translator_2.model.repository.RepositoryLocal
import com.example.translator_2.model.room.HistoryDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>>{ RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {

}