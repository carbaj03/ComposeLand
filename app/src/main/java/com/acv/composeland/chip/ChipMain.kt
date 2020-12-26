package com.acv.composeland.chip

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.acv.composeland.common.Body
import com.acv.composeland.common.H1


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
    state: ChipMainState
) {
    ScrollableColumn {
        H1(text = "Usage")
        Row {
            Body(text = "comsadf")
            Body(text = "Relevant")
            Body(text = "Focused")
        }
    }

//    ScrollableColumn {
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