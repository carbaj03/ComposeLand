package com.acv.composeland.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.R
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.common.TopBarBack
import com.acv.composeland.ui.common.fakeGridItems


data class NavigationState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<NavigationItem>,
)

data class NavigationItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun NavigationMain(navController: NavHostController) {
    val state = NavigationState(
        title = "Navigation",
        goBack = { navController.popBackStack() },
        items = listOf(
            NavigationItem(
                image = R.drawable.ic_compose,
                title = "asdf",
                description = "sdf",
                goToDetail = {}
            )
        )
    )

    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        }
    ) {
        LazyColumn {
            fakeGridItems(state.items, 2) { screen ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = { screen.goToDetail() })
                ) {
                    Image(
                        painter = painterResource(screen.image),
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
                    )

                    H6(text = screen.title)
                    Body(text = screen.description)
                }
            }
        }
    }
}