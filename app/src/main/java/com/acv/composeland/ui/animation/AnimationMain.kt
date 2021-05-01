package com.acv.composeland.ui.animation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H5
import com.acv.composeland.ui.common.H6

data class AnimationMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<AnimationMainItem>,
)

data class AnimationMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun AnimationMain(
    navigator: AnimationNavigator,
) {
    val state = AnimationMainState(
        title = "Animations",
        goBack = { navigator.goBack() },
        items = listOf(
            AnimationMainItem("", {})
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                }
            )
        },
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
            H5("If you are animating content change in layout")
            H6("If you are animating enter/exit transition")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goAnimationVisibility() })
            ) {
                Text(text = "AnimationVisibility")
            }
            H6("If you are animating changes to content size:")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goModifier() })
            ) {
                Text(text = "Modifier.contentSize")
            }
            H6("Use Crossfade")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goCrossfade() })
            ) {
                Text(text = "Crossfade")
            }

            H5("If the animation is state-based:")
            H6("If the animation happens during composition:")
            Body("If the animation is infinite")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goRememberInfiniteTransition() })
            ) {
                Text(text = "rememberInfiniteTransition")
            }
            Body("If you are animation multiple values simultaneously:")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goUpdateTransition() })
            ) {
                Text(text = "updateTransition")
            }
            Body("Otherwise, use animate*AsState.")

            H5(text = "If you want to have fine control over animation time:")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goAnimateAsState() })
            ) {
                Text(text = "animateAsState")
            }

            H5(text = "If the animation is the only source of truth")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goAnimatable() })
            ) {
                Text(text = "Animatable")
            }

            H5(text = "Otherwise, use AnimationState or animate.")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goAnimationState() })
            ) {
                Text(text = "AnimationState")
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goAnimate() })
            ) {
                Text(text = "animate()")
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goTargetBased() })
            ) {
                Text(text = "TargetBased()")
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { navigator.goSpec() })
            ) {
                Text(text = "Spec")
            }
        }
    }
}