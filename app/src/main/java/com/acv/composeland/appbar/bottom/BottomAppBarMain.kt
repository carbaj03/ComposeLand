package com.acv.composeland.appbar.bottom

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.appbar.bottom.design.*
import com.acv.composeland.appbar.bottom.implementation.Implementation
import com.acv.composeland.material.PrincipleItem
import com.acv.composeland.material.RelatedItem
import com.acv.composeland.screen.MaterialScreen


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
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun BottomAppBarMain(
    navController: NavHostController
) {
    var usageExpanded by remember { mutableStateOf(false) }
    var fabVisible by remember { mutableStateOf(false) }

    var state by remember {
        mutableStateOf(
            BottomAppBarMainState(
                title = "Bottom App Bar Examples",
                goBack = { navController.popBackStack() },
                description = "A bottom app bar displays navigation and key actions at the bottom of mobile screens.",
                design = DesignState(
                    usage = Usage(
                        isExpanded = usageExpanded,
                        onExpand = {
                            usageExpanded = !usageExpanded
                            Log.e("onexpand", usageExpanded.toString())
                        },
                        title = usageExpanded.toString(),
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
                    ),
                    anatomy = Anatomy(
                        title = "Anatomy",
                        description = "Bottom app bars can contain actions that apply to the context of the current screen. They include a navigation menu control on the far left and a floating action button (when one is present). If included in a bottom app bar, an overflow menu control is placed at the end of other actions.",
                        positioning = "Positioning",
                        fab = "Bottom app bars have three different layouts based on the presence of a FAB and its position in the bar. These layouts dictate the number of actions that can be included in the bar.",
                    ),
                    behaviour = Behaviour(
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
                    ),
                    theming = Theming(R.drawable.ic_compose),
                    specs = Specs(image = R.drawable.ic_compose),
                ),
                items = listOf(
                    BottomAppBarMainItem("Example", { navController.popBackStack() })
                ),
                onFabClick = { fabVisible = usageExpanded },
                fabVisible = fabVisible,
            )
        )
    }

    Scaffold(
        topBar = {
            BottomAppBatTopBar(state.title, state.goBack)
        },
        floatingActionButton = {
            if (state.fabVisible) {
                FloatingActionButton(onClick = { state.onFabClick() }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
    ) {
        MainTabs(
            items = listOf(DesignTabItem, ImplementationTabItem),
        ) { item ->
            when (item) {
                DesignTabItem -> Design(state = state.design)
                ImplementationTabItem -> Implementation(state)
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
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )
}