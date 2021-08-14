package com.acv.composeland.ui.appbar.bottom

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import com.acv.composeland.R
import com.acv.composeland.ui.appbar.bottom.design.*
import com.acv.composeland.ui.appbar.bottom.implementation.Implementation
import com.acv.composeland.ui.material.PrincipleItem
import com.acv.composeland.ui.material.RelatedItem
import com.acv.composeland.ui.screen.BottomAppBarScreen
import com.acv.composeland.ui.screen.MaterialScreen


data class BottomAppBarMainState(
    val title: String,
    val goBack: () -> Unit,
    val description: String,
    val design: DesignState,
    val items: List<BottomAppBarMainItem>,
    val fabVisible: Boolean,
    val onFabClick: () -> Unit,
)

data class BottomAppBarMainItem(
    val icon: Int,
    val name: String,
    val goToDetail: () -> Unit
)

@Composable
fun BottomAppBarMain(
    navController: NavHostController
) {
    var expanded by rememberSaveable { mutableStateOf(-1) }
    var fabVisible by rememberSaveable(expanded) { mutableStateOf(expanded >= 0) }

    var usageState = Usage(
        isExpanded = expanded == 0,
        onExpand = { expanded = if (expanded == 0) -1 else 0 },
        title = "Usage",
        description = "Bottom app bars provide access to a bottom navigation drawer and up to four actions, including the floating action button.",
        related = listOf(
            RelatedItem(
                action = { navController.navigate(route = MaterialScreen.Text.route) },
                title = "Navigation Drawer",
                icon = R.drawable.topbar,
                subTitle = "Navigation drawers provide access to destinations in your app.",
                type = "Related Article",
            ),
            RelatedItem(
                action = { navController.navigate(route = MaterialScreen.Button.route) },
                title = "Button Samples",
                icon = R.drawable.navigation_drawer,
                subTitle = "All The text samples",
                type = "Related Article"
            )
        ),
        principles = listOf(
            PrincipleItem(
                image = R.drawable.actionable,
                title = "Actionable",
                subTitle = "Bottom app bars highlight important screen actions and raise awareness of the floating action button.",
            ),
            PrincipleItem(
                image = R.drawable.flexible,
                title = "Flexible",
                subTitle = "A bottom app bar's layout and actions change based on the needs of the screen.",
            ),
            PrincipleItem(
                image = R.drawable.ergonomic,
                title = "Ergonomic",
                subTitle = "The bottom app bar is easy to reach from a handheld position on a mobile device.",
            )
        ),
        whenToUsage = "Use always"
    )

    var behaviourState = Behaviour(
        isExpanded = expanded == 1,
        onExpand = { expanded = if (expanded == 1) -1 else 1 },
        title = "Behaviour",
        layout = Layout(
            title = "Layout",
            description = "To support the intent of different sections of an app, the layout and actions of a bottom app bar can be changed to suit each screen.\n" +
                    "\n" +
                    "For example, screens can display more or fewer actions according to what suits the screen content best",
        ),
        elevation = "",
        placement = "",
        scolling = Scrolling(
            title = "Scrolling",
            description = "Upon scroll, the bottom app bar can appear or disappear:\n" +
                    "\n" +
                    "Scrolling downward hides the bottom app bar. If a FAB is present, it detaches from the bar and remains on screen.\n" +
                    "Scrolling upward reveals the bottom app bar, and reattaches to a FAB if one is present.\n" +
                    "A bottom app bar can contain a shape, such as a notch, along its edge to accommodate the FAB. As the bar detaches from the FAB, the bar returns to its default shape. Upon returning to the screen and reattaching to the FAB, the bar regains its notched shape.",
        )
    )

    var designState = DesignState(
        usage = usageState,
        behaviour = behaviourState,
        anatomy = Anatomy(
            title = "Anatomy",
            description = "Bottom app bars can contain actions that apply to the context of the current screen. They include a navigation menu control on the far left and a floating action button (when one is present). If included in a bottom app bar, an overflow menu control is placed at the end of other actions.",
            positioning = "Positioning",
            fab = "Bottom app bars have three different layouts based on the presence of a FAB and its position in the bar. These layouts dictate the number of actions that can be included in the bar.",
        ),
        theming = Theming(R.drawable.ic_compose),
        specs = Specs(image = R.drawable.ic_compose),
    )

    var state = BottomAppBarMainState(
        title = "Bottom App Bar Examples",
        goBack = { navController.popBackStack() },
        description = "A bottom app bar displays navigation and key actions at the bottom of mobile screens.",
        design = designState,
        items = listOf(
            BottomAppBarMainItem(
                icon = R.drawable.ic_click,
                name = "Background",
                goToDetail = { navController.navigate(route = BottomAppBarScreen.Background.route) }
            ),
            BottomAppBarMainItem(
                icon = R.drawable.ic_click,
                name = "Elevation",
                goToDetail = { navController.navigate(route = BottomAppBarScreen.Elevation.route) }
            ),
            BottomAppBarMainItem(
                icon = R.drawable.ic_click,
                name = "Content Color",
                goToDetail = { navController.navigate(route = BottomAppBarScreen.ContentColor.route) }
            ),
            BottomAppBarMainItem(
                icon = R.drawable.ic_click,
                name = "Cutout Shape",
                goToDetail = { navController.navigate(route = BottomAppBarScreen.CutoutShape.route) }
            ),
        ),
        onFabClick = { fabVisible = !fabVisible },
        fabVisible = fabVisible,
    )

    Scaffold(
        topBar = {
            BottomAppBatTopBar(state.title, state.goBack)
        },
        floatingActionButton = {
            if (fabVisible) {
                FloatingActionButton(onClick = { expanded = -1 }) {
                    Icon(Icons.Filled.Favorite, "Favorite")
                }
            }
        },
    ) {
        MainTabs(
            items = listOf(DesignTabItem, ImplementationTabItem),
        ) { item ->
            when (item) {
                DesignTabItem -> Design(state = state.design)
                ImplementationTabItem -> Implementation(state = state)
            }
        }
    }
}

@Composable
private fun BottomAppBatTopBar(
    title: String,
    goBack: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        }
    )
}