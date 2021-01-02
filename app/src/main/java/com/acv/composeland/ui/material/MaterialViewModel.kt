package com.acv.composeland.ui.material

import androidx.lifecycle.ViewModel

class MaterialViewModel : ViewModel() {

    suspend fun init(mainNavigator: MaterialNavigator): MaterialState =
        getState(mainNavigator = mainNavigator)

    override fun onCleared() {
        super.onCleared()

    }
}