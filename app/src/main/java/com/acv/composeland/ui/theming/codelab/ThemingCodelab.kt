package com.acv.composeland.ui.theming.codelab

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import arrow.optics.optics
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H4
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.common.TopBarBack
import kotlinx.coroutines.launch


data class ThemingCodelabState(
    val title: String,
    val goBack: () -> Unit,
) {
    companion object {
        fun empty(navigator: ThemingCodelabNavigator) =
            ThemingCodelabState(
                title = "Jetpack Compose Theming",
                goBack = { navigator.goBack() },
            )
    }
}

@Composable
fun ThemingCodelab(
    navigator: ThemingCodelabNavigator,
) {
    val state = ThemingCodelabState.empty(navigator)
    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BackdropScaffoldSample(
    navigator: ThemingCodelabNavigator,
) {
    val states = listOf(
        StepsStateData(Steps.Introduction),
        StepsStateData(Steps.SetUp),
        StepsStateData(Steps.Theming),
        StepsStateData(Steps.Define),
        StepsStateData(Steps.Color),
        StepsStateData(Steps.Text),
        StepsStateData(Steps.Shapes),
    )
    val scope = rememberCoroutineScope()
    var selection: List<StepsStateData> by remember { mutableStateOf(states) }
    var selected: Int by remember { mutableStateOf(selection.first().step.number) }
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text("Jetpack Compose Theming") },
                navigationIcon = {
                    if (scaffoldState.isConcealed) {
                        IconButton(onClick = {
                            scope.launch { scaffoldState.reveal() }
                        }) {
                            Icon(Icons.Default.Menu, null)
                        }
                    } else {
                        IconButton(onClick = {
                            scope.launch { scaffoldState.conceal() }
                        }) {
                            Icon(Icons.Default.Close, null)
                        }
                    }
                },
                actions = {
                    var clickCount by remember { mutableStateOf(0) }
                    IconButton(
                        onClick = {
                            // show snackbar as a suspend function
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Snackbar #${++clickCount}")
                            }
                        }
                    ) {
                        Icon(Icons.Default.Favorite, null)
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backLayerBackgroundColor = Color.LightGray.copy(alpha = 0.25f),
        backLayerContent = {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "About this codelab",
                            style = MaterialTheme.typography.h6,
                        )
                        Text(
                            text = "Last updated Dec 17, 2020",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Text(
                            text = " Written by Nick Butcher",
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
                selection.forEach { state ->
                    item {
                        Step(
                            step = state.step,
                            state = state.stepState,
                            onSelected = {
                                selection = select(selection, state.step.number)
                                selected = state.step.number
                                scope.launch { scaffoldState.conceal() }
                            }
                        )
                    }
                }
            }
        },
        frontLayerElevation = 8.dp,
        stickyFrontLayer = false,
//        frontLayerBackgroundColor = MaterialTheme.colors.primary,
//        frontLayerScrimColor = MaterialTheme.colors.primary.copy(alpha = 0.14f),
        frontLayerContent = {
            when (Steps.fromNumber(selected)) {
                Steps.Introduction -> FirstStep(onNext = {
                    selection = select(selection, Steps.SetUp.number)
                    selected = Steps.SetUp.number
                })
                Steps.SetUp -> SecondStep(
                    onBack = {
                        selection = select(selection, Steps.Introduction.number)
                        selected = Steps.Introduction.number
                    },
                    onNext = {
                        selection = select(selection, Steps.Theming.number)
                        selected = Steps.Theming.number
                    }
                )
                Steps.Theming -> ThirdStep(
                    onBack = {
                        selection = select(selection, Steps.SetUp.number)
                        selected = Steps.SetUp.number
                    },
                    onNext = {
                        selection = select(selection, Steps.Define.number)
                        selected = Steps.Define.number
                    }
                )
                Steps.Define -> FirstStep(onNext = {
                    selection = select(selection, Steps.Color.number)
                    selected = Steps.Color.number
                })
                Steps.Color -> FirstStep(onNext = {
                    selection = select(selection, Steps.Color.number)
                    selected = Steps.Color.number
                })
                Steps.Text -> FirstStep(onNext = {
                    selection = select(selection, Steps.Text.number)
                    selected = Steps.Text.number
                })
                Steps.Shapes -> FirstStep(onNext = {
                    selection = select(selection, Steps.Introduction.number)
                    selected = Steps.Color.number
                })
            }
        })
}

fun select(
    selection: List<StepsStateData>,
    number: Int,
): List<StepsStateData> =
    selection.map {
        when {
            it.step.number == number ->
                it.copy(stepState = StepState.Selected)
            it.step.number >= number ->
                it.copy(stepState = StepState.Pending)
            else ->
                it.copy(stepState = StepState.Completed)
        }
    }

enum class StepState {
    Completed, Selected, Pending
}

@Composable
fun Step(
    step: Steps,
    state: StepState,
    onSelected: () -> Unit,
) {
    Log.e("Step", step.title)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onSelected() },
        elevation = when (state) {
            StepState.Completed -> 0.dp
            StepState.Selected -> 8.dp
            StepState.Pending -> 0.dp
        },
        border = BorderStroke(1.dp, SolidColor(Color.Black.copy(alpha = 0.15f))),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .defaultMinSize(50.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(46.dp)
                    .padding(8.dp)
                    .background(
                        when (state) {
                            StepState.Completed -> Color.Blue
                            StepState.Selected -> Color.Blue
                            StepState.Pending -> Color.LightGray
                        }, RoundedCornerShape(50)
                    ),
            ) {
                Text(
                    text = step.number.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = step.title,
            )
        }
    }
}

//enum class Steps(
//    val title: String,
//    val number: Int,
//    val state: StepState
//) {
//    Introduction("Introduction", 1, StepState.Pending),
//    SetUp("Getting set up", 2, StepState.Pending),
//    Theming("Material theming", 3, StepState.Pending),
//    Define("Define your theme", 4, StepState.Pending),
//    Color("Working with color", 5, StepState.Pending),
//    Text("Working with text", 6, StepState.Pending),
//    Shapes("Working with shapes", 7, StepState.Pending),
//}

@optics
data class StepsStateData(
    val step: Steps,
    val stepState: StepState = StepState.Pending,
) {
    companion object
}

sealed class Steps(
    open val title: String,
    open val number: Int,
) {
    companion object {
        fun fromNumber(number: Int) =
            when (number) {
                Introduction.number -> Introduction
                SetUp.number -> SetUp
                Theming.number -> Theming
                Define.number -> Define
                Color.number -> Color
                Text.number -> Text
                Shapes.number -> Shapes
                else -> Introduction
            }
    }

    object Introduction : Steps("Introduction", 1)
    object SetUp : Steps("Getting set up", 2)
    object Theming : Steps("Material theming", 3)
    object Define : Steps("Define your theme", 4)
    object Color : Steps("Working with color", 5)
    object Text : Steps("Working with text", 6)
    object Shapes : Steps("Working with shapes", 7)
}


@Composable
fun FirstStep(
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        H6(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(24.dp),
            text = "1. Introduction"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Body(
                text = "In this codelab you will learn how to use Jetpack Compose‘s theming APIs to style your application. We'll see how to customize colors, shapes and typography so that they're used consistently throughout your application, supporting multiple themes such as light & dark theme."
            )
            H6(text = "What you will learn")
            Body(
                """
                            In this codelab, you will learn:

                            A primer in Material Design and how you can customize it for your brand
                            · How Compose implements the Material Design system
                            · How to define and use colors, typography and shapes throughout your app
                            · How to style components
                            · How to support light and dark themes
                            · A primer in implementing your own design system
                        """.trimIndent(),
            )

            H4(text = "What you will build")

            Body(text = "In this codelab we will style a news-reading app. We begin with an unstyled application and will apply what we learn to theme the application and support dark themes.")

            Body(
                """
                        Prerequisites
                            · Experience with Kotlin syntax, including lambdas
                            · Basic understanding of Compose.
                            · Basic familiarity with Compose layouts e.g. Row , Column & Modifier
                        """.trimIndent()
            )
        }
//        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.padding(16.dp)) {
//            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onNext() }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun SecondStep(
    onNext: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        H6(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(24.dp),
            text = "2. Getting set up"
        )
        Body(text = "In this step, you will download the code for this which comprises a simple news-reader app that we will style.")
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { onBack() }) {
                Text(text = "Back")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onNext() }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ThirdStep(
    onNext: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        H6(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(24.dp),
            text = "3. Material Theming"
        )
        Body(
            text = """
                Jetpack Compose offers an implementation of Material Design—a comprehensive design system for creating digital interfaces. The Material Design components (Buttons, Cards, Switches etc) are built on top of Material Theming which is a systematic way to customize Material Design to better reflect your product's brand. A Material Theme comprises color, typography and shape attributes. Customizing these will be automatically reflected in the components you use to build your app.

                An understanding of Material Theming is helpful to understand how to theme your Jetpack Compose apps so here's a brief description of the concepts. If you're already familiar with Material Theming, you can skip forward.
            """.trimIndent()
        )
        H6(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(24.dp),
            text = "Color"
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { onBack() }) {
                Text(text = "Back")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onNext() }) {
                Text(text = "Next")
            }
        }
    }
}