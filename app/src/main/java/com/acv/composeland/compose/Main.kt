package com.acv.composeland.compose

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.screen.MainScreen
import com.acv.composeland.screen.MaterialScreen
import kotlinx.coroutines.delay


data class MainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<MainItem>,
)

sealed class MainItem

data class HeaderMainItem(
    val image: Int,
    val title: String,
    val description: String,
    val guide: () -> Unit,
    val codelab: () -> Unit,
) : MainItem()

data class MidMainItem(
    val image: Int,
    val title: String,
    val description: String,
    val guide: () -> Unit,
    val codelab: () -> Unit,
) : MainItem()

suspend fun getState(navController: NavHostController): MainState {
    delay(2000)
    return MainState(
        title = "ComposeLand",
        goBack = { navController.popBackStack() },
        items = listOf(
            HeaderMainItem(
                image = R.drawable.ic_core_cocepts,
                title = "Core2",
                description = "Core description",
                guide = { navController.navigate(MainScreen.Core.route) },
                codelab = {},
            ),
            MidMainItem(
                image = R.drawable.ic_compose,
                title = "Material",
                description = "Design guidance and code",
                guide = { navController.navigate(MaterialScreen.Main.route) },
                codelab = {},
            )
        )
    )
}


@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val id = 0
    var state by remember {
        mutableStateOf(
            MainState(
                title = "ComposeLand",
                goBack = { navController.popBackStack() },
                items = listOf(
                    HeaderMainItem(
                        image = R.drawable.ic_core_cocepts,
                        title = "Core",
                        description = "Core description",
                        guide = { navController.navigate(MainScreen.Core.route) },
                        codelab = {},
                    ),
                    MidMainItem(
                        image = R.drawable.ic_compose,
                        title = "Material",
                        description = "Design guidance and code",
                        guide = { navController.navigate(MaterialScreen.Main.route) },
                        codelab = {},
                    )
                )
            )
        )
    }
    LaunchedEffect(id) { state = getState(navController = navController) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
            )
        },
    ) {
        ScrollableColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            state.items.forEach { item ->
                Spacer(modifier = Modifier.height(8.dp))
                when (item) {
                    is HeaderMainItem -> {
                        CustomCard(
                            imageVector = vectorResource(id = item.image),
                            title = item.title,
                            description = item.description,
                            type = "Featured",
                            guide = item.guide,
                            codelab = item.codelab,
                        )
                    }
                    is MidMainItem -> {
                        CustomMid(
                            imageVector = vectorResource(id = item.image),
                            title = item.title,
                            description = item.description,
                            guide = item.guide,
                            codelab = item.codelab
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}