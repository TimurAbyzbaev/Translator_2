package com.example.translator_2.di.koin

import com.example.translator_2.Interactors.MainInteractor
import com.example.translator_2.api.DataModel
import com.example.translator_2.di.koin.NAME_REMOTE
import com.example.translator_2.di.koin.NAME_LOCAL
import com.example.translator_2.presentation.viewmodels.MainViewModel
import com.example.translator_2.repository.Repository
import com.example.translator_2.repository.RepositoryImplementation
import com.example.translator_2.repository.RetrofitImplementation
import com.example.translator_2.repository.RoomDataBaseImplementation
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