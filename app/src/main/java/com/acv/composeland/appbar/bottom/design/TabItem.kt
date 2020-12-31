package com.acv.composeland.appbar.bottom.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(
    val title: String,
    val icon: ImageVector,
    val new: Boolean,
)

object DesignTabItem : TabItem(
    title = "Design",
    icon = Icons.Filled.Menu,
    new = false,
)

object ImplementationTabItem : TabItem(
    title = "Implementation",
    icon = Icons.Filled.AccountBox,
    new = true,
)