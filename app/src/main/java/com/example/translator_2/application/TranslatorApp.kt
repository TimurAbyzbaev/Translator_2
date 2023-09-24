package com.example.translator_2.application

import android.app.Application
import com.example.translator_2.di.koin.application
import com.example.translator_2.di.koin.mainScreen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.koin.core.context.startKoin
import javax.inject.Inject

// Обратите внимание на dispatchingAndroidInjector и интерфейс Dagger'а
// HasAndroidInjector: мы переопределяем его метод androidInjector. Они
// нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные
//методы для разработчиков под Андроид для эффективного внедрения компонентов
//платформы, таких как Активити, Сервис и т. п.

class TranslatorApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}