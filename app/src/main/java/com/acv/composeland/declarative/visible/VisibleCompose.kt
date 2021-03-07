package com.acv.composeland.declarative.visible

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.suspend.launch
import com.acv.composeland.ui.compose.padding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay


class VisibleCompose : AppCompatActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)
        setContent {
//            ListWithBug(myList = (1..1000).map { it.toString() })
           ListHeaders()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ListHeaders(){
        var items by remember { mutableStateOf(arrayOf("")) }
        var loading by remember { mutableStateOf(true) }
        LaunchedEffect(service) {
            items = service.fetchData()
            loading = false
        }

        if (loading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                stickyHeader {
                    Text("Stycky")
                }
                items.forEach { text ->
                    item {
                        Text(modifier = Modifier.padding(100.dp) , text = text)
                    }
                }
                stickyHeader {
                    Text("Stycky")
                }
                items.forEach { text ->
                    item {
                        Text(modifier = Modifier.padding(100.dp) , text = text)
                    }
                }
            }
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    @Composable
    fun ListWithBug(myList: List<String>) {
        var items by remember { mutableStateOf(0)}
        val scope = rememberCoroutineScope()
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.animateContentSize()) {
                for (item in myList) {

                    Text("Item: $item")
                    scope.launch {
                        delay(1000)
                    items++ // Avoid! Side-effect of the column recomposing.
                    }
                }
            }
            Text("Count: $items")
        }
    }
}