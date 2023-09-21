package com.example.translator_2.View

import com.example.translator_2.AppState

interface View {
    fun renderData(appState: AppState)
}