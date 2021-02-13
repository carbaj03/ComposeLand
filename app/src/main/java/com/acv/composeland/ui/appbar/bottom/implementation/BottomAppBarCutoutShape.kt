package com.acv.composeland.ui.appbar.bottom.implementation

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt

data class BottomAppBarCutoutShapeState(
    val goBack: () -> Unit,
)

@Composable
fun BottomAppBarCutoutShape(
    navController: NavHostController
) {
    val state = BottomAppBarCutoutShapeState(goBack = { navController.popBackStack() })

    val a: HashMap<String, Shape> = hashMapOf(
        "CutCorner" to CutCornerShape(50),
        "Circle" to CircleShape,
    )
    var color by remember { mutableStateOf(a.keys.elementAt(0)) }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                color = Color.Blue
            )
      }
    """

    CodeScaffold(
        title = "BottomAppBarCutoutShape",
        goBack = state.goBack,
        code = code,
        options = {
            ChipGroup(
                items = a.keys.toList(),
                selected = a.keys.elementAt(0),
                onChange = { color = it }
            )
        },
        sample = {
            Scaffold(
                modifier = Modifier.height(100.dp),
                bottomBar = {
                    BottomAppBar(
                        cutoutShape = a[color]!!,
                    ) {}
                },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text("Change shape") },
                        onClick = {},
                        shape = a[color]!!,
                    )
                },
                floatingActionButtonPosition = FabPosition.Center,
                isFloatingActionButtonDocked = true,
            ) {}
        },
    )

}

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    val scaffoldState = rememberScaffoldState()

    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val sharpEdgePercent = -50f
    val roundEdgePercent = 45f
    // Start with sharp edges
    val animatedProgress = animatedFloat(sharpEdgePercent)
    // animation value to animate shape
    val progress = animatedProgress.value.roundToInt()

    // When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
    // This allows for a smooth transition between cut corners and round corners.
    val fabShape = if (progress < 0) {
        CutCornerShape(abs(progress))
    } else if (progress == roundEdgePercent.toInt()) {
        CircleShape
    } else {
        RoundedCornerShape(progress)
    }
    // lambda to call to trigger shape animation
    val changeShape = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
        animatedProgress.animateTo(
            targetValue = nextTarget,
            anim = TweenSpec(durationMillis = 600)
        )
    }

    Scaffold(
        modifier = Modifier.height(300.dp),
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topBar = { TopAppBar(title = { Text("Scaffold with bottom cutout") }) },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {
                IconButton(onClick = {
                    scaffoldState.drawerState.open()
                }) {
                    Icon(Icons.Filled.Menu, null)
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Change shape") },
                onClick = changeShape,
                shape = fabShape
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bodyContent = { innerPadding ->

        }
    )
}
