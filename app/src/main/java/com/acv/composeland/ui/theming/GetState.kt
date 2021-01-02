package com.acv.composeland.ui.theming

import com.acv.composeland.R
import kotlinx.coroutines.delay

suspend fun getState(
    mainNavigator: ThemingNavigator,
): ThemingState {
    delay(2000)
    return ThemingState(
        title = "Theming updated",
        goBack = { mainNavigator.goBack() },
        componentsThemingState = ComponentsThemingState(
            listOf(
                ThemingItem(
                    image = R.drawable.bottom,
                    title = "App bars: bottom",
                    description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                    goToDetail = { mainNavigator.goBottomAppBar() },
                ),
                ThemingItem(
                    image = R.drawable.buttons,
                    title = "Buttons",
                    description = "Buttons allow users to take actions, and make choices, with a single tap ",
                    goToDetail = { mainNavigator.goButton() }
                ),
                ThemingItem(
                    image = R.drawable.bottom,
                    title = "Text",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { mainNavigator.goText() },
                ),
            )
        ),
        designThemingState = DesignThemingState(
            listOf(
                ThemingItem(
                    image = R.drawable.snackbars,
                    title = "Color",
                    description = "sdf",
                    goToDetail = { mainNavigator.goColor() }
                )
            )
        ),
    )
}