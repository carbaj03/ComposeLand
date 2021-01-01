package com.acv.composeland.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.common.fakeGridItems
import com.acv.composeland.screen.ButtonScreen


data class ButtonMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<ButtonMainItem>,
)

data class ButtonMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ButtonMain(
    navController: NavHostController
) {
    val state = ButtonMainState(
        title = "Button Examples",
        goBack = { navController.popBackStack() },
        items = listOf(
            ButtonMainItem(
                name = "Color",
                goToDetail = { navController.navigate(ButtonScreen.Color.route) }
            )
        ),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
    ) {
        LazyColumn {
            fakeGridItems(state.items, 2) { screen ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = { screen.goToDetail() })
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { screen.goToDetail() })
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = screen.name,
                        )
                    }
                }
            }
        }
    }

}