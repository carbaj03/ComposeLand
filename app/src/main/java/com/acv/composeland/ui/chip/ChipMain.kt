package com.acv.composeland.ui.chip

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H1


data class ChipMainState(
    val goBack: () -> Unit,
    val items: List<ChipMainItem>,
)

data class ChipMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ChipMain(
    navController : NavHostController
) {
    val state = ChipMainState(
        goBack = { navController.popBackStack() },
        items = ChipScreen.items(navController),
    )
    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        H1(text = "Usage")
        Row {
            Body(text = "comsadf")
            Body(text = "Relevant")
            Body(text = "Focused")
        }
    }

//    Column(modifier = Modifier.verticalScroll(rememberScrollState())
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .clickable(onClick = { navController.navigate(ChipScreen.Color.route) })
//        ) {
//            Text(text = "Color")
//        }
//        Text(text = "Usage", style = MaterialTheme.typography.h4)
//        Text(text = "Chips allow users to enter information, make selections, filter content, or trigger actions. While buttons are expected to appear consistently and with familiar calls to action, chips should appear dynamically as a group of multiple interactive elements.")
//        Text(text = "Priciples", style = MaterialTheme.typography.h6)
//        Row {
//            Text(text = "Compact")
//            Text(text = "Relevant")
//            Text(text = "Focused")
//        }
//        Text(text = "Types")
//
//    }

}