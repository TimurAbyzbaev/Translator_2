package com.example.translator_2.di

import com.example.translator_2.Data_source.DataSource
import com.example.translator_2.Data_source.DataSourceRemote
import com.example.translator_2.api.DataModel
import com.example.translator_2.repository.Repository
import com.example.translator_2.repository.RepositoryImplementation
import com.example.translator_2.repository.RetrofitImplementation
import com.example.translator_2.repository.RoomDataBaseImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote:
    DataSource<List<DataModel>>) : Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal:
                                            DataSource<List<DataModel>>) : Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun providesDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun providesDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}