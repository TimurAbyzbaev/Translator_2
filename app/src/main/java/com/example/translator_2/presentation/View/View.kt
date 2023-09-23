package com.example.translator_2.presentation.View

import com.example.translator_2.AppState

interface View {
    fun renderData(appState: AppState)
}