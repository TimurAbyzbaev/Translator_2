package com.example.translator_2.di.koin

import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import com.example.translator_2.model.repository.Repository
import com.example.translator_2.model.repository.RepositoryImplementation
import com.example.translator_2.model.Data_source.RetrofitImplementation
import com.example.translator_2.model.Data_source.RoomDataBaseImplementation
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}