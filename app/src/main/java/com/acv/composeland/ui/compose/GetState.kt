package com.acv.composeland.ui.compose

import com.acv.composeland.R
import kotlinx.coroutines.delay

suspend fun getState(mainNavigator: MainNavigator): MainState {
    delay(2000)
    return MainState(
        title = "ComposeLand",
        goBack = { mainNavigator.goBack() },
        items = listOf(
            HeaderMainItem(
                image = R.drawable.ic_core_cocepts,
                title = "Core Update",
                description = "Core description",
                guide = { mainNavigator.goCore() },
                codelab = {},
            ),
            MidMainItem(
                image = R.drawable.ic_compose,
                title = "Material Update",
                description = "Design guidance and code",
                guide = { mainNavigator.goMain() },
                codelab = {},
            )
        )
    )
}