package com.acv.composeland.ui.theming

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ThemingViewModel : ViewModel() {

    fun init(mainNavigator: ThemingNavigator): Flow<ThemingState> =
        flow {
            emit(getState(mainNavigator = mainNavigator))
        }
}