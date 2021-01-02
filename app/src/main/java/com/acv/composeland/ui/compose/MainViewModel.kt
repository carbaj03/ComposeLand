package com.acv.composeland.ui.compose

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    suspend fun init(mainNavigator: MainNavigator) =
        getState(mainNavigator = mainNavigator)

    override fun onCleared() {
        super.onCleared()

    }
}