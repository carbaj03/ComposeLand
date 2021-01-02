package com.acv.composeland.ui.core

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H6

data class CoreConceptsMainState(
    val title: String,
    val goBack: () -> Unit,
)

@Composable
fun CoreConceptsMain(
    navController: NavHostController
) {
    val state = CoreConceptsMainState(
        title = "Core",
        goBack = { navController.popBackStack() },
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { H6(text = state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
    ) {
        ScrollableColumn(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Body("Jetpack Compose is a modern declarative UI Toolkit for Android. Compose makes it easier to write and maintain your app UI by providing a declarative API that allows you to render your app UI without imperatively mutating frontend views. This terminology needs some explanation, but the implications are important for your app design.")

            H6(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "The declarative programming paradigm"
            )

            Body(
                text = "Historically, an Android view hierarchy has been representable as a tree of UI widgets. As the state of the app changes because of things like user interactions, the UI hierarchy needs to be updated to display the current data. The most common way of updating the UI is to walk the tree using functions like findViewById(), and change nodes by calling methods like button.setText(String), container.addChild(View), or img.setImageBitmap(Bitmap). These methods change the internal state of the widget.\n" +
                        "\n" +
                        "Manipulating views manually increases the likelihood of errors. If a piece of data is rendered in multiple places, it’s easy to forget to update one of the views that shows it. It’s also easy to create illegal states, when two updates conflict in an unexpected way. For example, an update might try to set a value of a node that was just removed from the UI. In general, the software maintenance complexity grows with the number of views that require updating.\n" +
                        "\n" +
                        "Over the last several years, the entire industry has started shifting to a declarative UI model, which greatly simplifies the engineering associated with building and updating user interfaces. The technique works by conceptually regenerating the entire screen from scratch, then applying only the necessary changes. This approach avoids the complexity of manually updating a stateful view hierarchy. Compose is a declarative UI framework.\n" +
                        "\n" +
                        "One challenge with regenerating the entire screen is that it is potentially expensive, in terms of time, computing power, and battery usage. To mitigate this cost, Compose intelligently chooses which parts of the UI need to be redrawn at any given time. This does have some implications for how you design your UI components, as discussed in Recomposition."
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}