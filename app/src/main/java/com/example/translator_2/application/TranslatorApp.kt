package com.example.translator_2.application

import android.app.Application
import com.example.translator_2.di.koin.application
import com.example.translator_2.di.koin.historyScreen
import com.example.translator_2.di.koin.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Обратите внимание на dispatchingAndroidInjector и интерфейс Dagger'а
// HasAndroidInjector: мы переопределяем его метод androidInjector. Они
// нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные
//методы для разработчиков под Андроид для эффективного внедрения компонентов
//платформы, таких как Активити, Сервис и т. п.

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}