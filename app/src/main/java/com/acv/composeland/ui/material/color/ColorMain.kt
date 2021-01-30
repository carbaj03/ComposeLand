package com.acv.composeland.ui.material.color

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.R
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.common.Subtitle
import com.acv.composeland.ui.material.PrincipleItem
import com.acv.composeland.ui.material.Principles

data class ColorMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<ColorMainItem>,
)

data class ColorMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ColorMain(
    navController: NavHostController
) {
    val state = ColorMainState(
        title = "Colors",
        goBack = { navController.popBackStack() },
        items = listOf(ColorMainItem(name = "sadf", goToDetail = {}))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
    ) {
//        Column(modifier = Modifier.verticalScroll(rememberScrollState())
//            state.items.forEach { screen ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable(onClick = { screen.goToDetail() })
//                ) {
//                    Text(text = screen.name)
//                }
//            }
//        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            TheColorSystem()
            ColorUsageAndPalettes()
            ColorThemeCreation()
            PickingColors()
        }
    }
}

@Composable
fun TheColorSystem() {

}

@Composable
fun ColorUsageAndPalettes() {

    H6(text = "Color usage and palettes")
    Body(text = "The Material Design color system helps you apply color to your UI in a meaningful way. In this system, you select a primary and a secondary color to represent your brand. Dark and light variants of each color can then be applied to your UI in different ways.")

    H6(text = "Colors and theming")
    Body(
        text = """
            Color themes are designed to be harmonious, ensure accessible text, and distinguish UI elements and surfaces from one another.

            The Material Design palette tool or 2014 Material Design palettes are available to help you select colors.
            """.trimIndent()
    )

    H6(
        modifier = Modifier.padding(vertical = 8.dp),
        text = "Principles"
    )

    Principles(
        items = listOf(
            PrincipleItem(
                image = R.drawable.hierarchical,
                title = "Hierarchical",
                subTitle = "Color indicates which elements are interactive, how they relate to other elements, and their level of prominence. Important elements should stand out the most.",
            ),
            PrincipleItem(
                image = R.drawable.legible,
                title = "Legible",
                subTitle = "Text and important elements, like icons, should meet legibility standards when appearing on colored backgrounds.",
            ),
            PrincipleItem(
                image = R.drawable.expressive,
                title = "Expressive",
                subTitle = "Show brand colors at memorable moments that reinforce your brand’s style.",
            )
        )
    )
}

@Composable
fun ColorThemeCreation() {
    Column {
        H6(text = "The baseline Material color theme")
        Body(
            text = """
        Material Design comes designed with a built-in, baseline theme that can be used as-is, straight out of the proverbial box.

        This includes default colors for:

        · Primary and secondary colors
        · Variants of primary and secondary colors
        · Additional UI colors, such as colors for backgrounds, surfaces, errors, typography, and iconography.
        
        All of these colors can be customized for your app.
    """.trimIndent()
        )

        H6(text = "Colors and theming")
        Body(
            text = """Color themes are designed to be harmonious, ensure accessible text, and distinguish UI elements and surfaces from one another.

        The Material Design palette tool or 2014 Material Design palettes are available to help you select colors.
        """.trimIndent()
        )

        PrimaryColor()
        SecondaryColor()
        SurfaceBackgroundErrorColors()
        TypographyIconographyColors()
    }
}

@Composable
fun PrimaryColor() {
    Column {
        H6(text = "Primary color")
        Body(text = "A primary color is the color displayed most frequently across your app's screens and components.")

        Subtitle(text = "Dark and light primary variants")
        Body(text = "Your primary color can be used to make a color theme for your app, including dark and light primary color variants.")

        Subtitle(text = "Distinguish UI elements")
        Body(text = "To create contrast between UI elements, such as a top app bar from a system bar, you can use light or dark variants of your primary colors. You can also use these to distinguish elements within a component, such as the icon of a floating action button from its circular container.")
    }
}

@Composable
fun SecondaryColor() {
    Column {
        H6(text = "Secondary color")
        Body(
            text = """
        A secondary color provides more ways to accent and distinguish your product. Having a secondary color is optional, and should be applied sparingly to accent select parts of your UI.

        If you don’t have a secondary color, your primary color can also be used to accent elements.

        Secondary colors are best for:

        · Floating action buttons
        · Selection controls, like sliders and switches
        · Highlighting selected text
        · Progress bars
        · Links and headlines
    """.trimIndent()
        )

        Subtitle(text = "Dark and light secondary variants")
        Body(text = "Just like the primary color, your secondary color can have dark and light variants. A color theme can use your primary color, secondary color, and dark and light variants of each color.")
    }
}

@Composable
fun SurfaceBackgroundErrorColors() {
    Column {

        H6(text = "Surface, background, and error colors")
        Body(
            text = """
        Surface, background, and error colors typically don’t represent brand:

        ·Surface colors affect surfaces of components, such as cards, sheets, and menus.
        ·The background color appears behind scrollable content. The baseline background and surface color is #FFFFFF.
        ·Error color indicates errors in components, such as invalid text in a text field. The baseline error color is #B00020.
    """.trimIndent()
        )

        Body(
            text = """
        A UI showcasing the baseline colors for background, surface, and error color:

        1. Baseline background color: #FFFFFF
        2. Baseline surface color: #FFFFFF
        3. Baseline error color: #B00020
    """.trimIndent()
        )
    }
}


@Composable
fun TypographyIconographyColors() {
    Column {
        H6(text = "On colors")
        Body(
            text = """
            App surfaces use colors from specific categories in your color palette, such as a primary color. Whenever elements, such as text or icons, appear in front of those surfaces, those elements should use colors designed to be clear and legible against the colors behind them.

            This category of colors is called “on” colors, referring to the fact that they color elements that appear “on” top of surfaces that use the following colors: a primary color, secondary color, surface color, background color, or error color. When a color appears “on” top of a primary color, it’s called an “on primary color.” They are labelled using the original color category (such as primary color) with the prefix “on.”

            “On” colors are primarily applied to text, iconography, and strokes. Sometimes, they are applied to surfaces.

            The default values for “on” colors are #FFFFFF and #000000.
        """.trimIndent()
        )
        Analitycs()
    }
}


@Composable
fun PickingColors() {

}

@Composable
fun Analitycs() {
    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Analitycs") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Search, "Search")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Filled.Add, "Add")
                }
            },
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                SimpleCardAnalitycs()
            }
        }
    }
}


@Composable
fun SimpleCardAnalitycs() {
    Card() {
        Column {
            Subtitle(text = "Marketing")
            H6(text = "243.3 M")
        }
    }
}