package com.acv.composeland.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acv.composeland.R


data class MainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<MainItem>,
) {
    companion object {
        fun empty(navController: MainNavigator) = MainState(
            title = "ComposeLand",
            goBack = { navController.goBack() },
            items = listOf(
                HeaderMainItem(
                    image = R.drawable.ic_core_cocepts,
                    title = "Core",
                    description = "Core description",
                    guide = { },
                    codelab = { },
                ),
                MidMainItem(
                    image = R.drawable.ic_compose,
                    title = "Material",
                    description = "Design guidance and code",
                    guide = { },
                    codelab = { },
                )
            )
        )
    }
}

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


@Composable
fun HomeScreen(
    navigation: MainNavigator,
    mainViewModel: MainViewModel = viewModel(),
    id: Int = 0,
) {
    Log.e("HomeScreen", "run")
    var state by remember(id) { mutableStateOf(MainState.empty(navigation)) }
    LaunchedEffect(id) { state = mainViewModel.init(navigation) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
            )
        },
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())) {
            state.items.forEach { item ->
                Spacer(modifier = Modifier.height(8.dp))
                when (item) {
                    is HeaderMainItem -> {
                        CustomCard(
                            painter = painterResource(id = item.image),
                            title = item.title,
                            description = item.description,
                            type = "Featured",
                            guide = item.guide,
                            codelab = item.codelab,
                        )
                    }
                    is MidMainItem -> {
                        CustomMid(
                            painter = painterResource(id = item.image),
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