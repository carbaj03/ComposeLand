package com.acv.composeland.ui.theming

import androidx.lifecycle.ViewModel

class ThemingViewModel : ViewModel() {

    suspend fun init(mainNavigator: ThemingNavigator): ThemingState =
        getState(mainNavigator = mainNavigator)

    override fun onCleared() {
        super.onCleared()

    }
}