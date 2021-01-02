package com.acv.composeland.common

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun CodeScaffold(
    title : String,
    goBack: () -> Unit,
    code: AnnotatedString,
    sample: @Composable () -> Unit,
    options: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
    ) {
        ScrollableColumn(contentPadding = PaddingValues(8.dp)) {
            TextCode(code = code)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Options",
                style = MaterialTheme.typography.h6
            )
            options()
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Result",
                style = MaterialTheme.typography.h6
            )
            sample()
        }
    }
}

@Composable
fun CodeScaffold(
    title : String,
    goBack: () -> Unit,
    code: String,
    sample: @Composable () -> Unit,
    options: @Composable () -> Unit,
) {
    CodeScaffold(
        goBack = goBack,
        title = title,
        code = buildAnnotatedString { append(code) },
        sample = sample,
        options = options
    )
}

@Composable
fun CodeScaffold(
    title : String,
    goBack: () -> Unit,
    code: String,
    content: @Composable () -> Unit,
) {
    CodeScaffold(
        title = title,
        goBack = goBack,
        code = buildAnnotatedString { append(code) },
        sample = content,
        options = {}
    )
}

@Composable
fun CodeScaffold(
    title : String,
    goBack: () -> Unit,
    code: AnnotatedString,
    children: @Composable () -> Unit,
) {
    CodeScaffold(
        title = title,
        goBack = goBack,
        code = code,
        sample = children,
        options = {}
    )
}