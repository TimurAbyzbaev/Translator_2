package com.example.translator_2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.translator_2.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {
    //фабрика
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) :
            ViewModelProvider.Factory
    //метод сообщает даггеру, что надо поместить эту модель в список (map)
    //моделей, используя аннотацию @IntoMap, где в качестве ключа будет
    //класс MainViewModel:class
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}