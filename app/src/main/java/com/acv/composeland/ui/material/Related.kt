package com.acv.composeland.ui.material

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acv.composeland.R

data class RelatedItem(
    val action: () -> Unit,
    val icon: Int,
    val title: String,
    val subTitle: String,
    val type: String,
)

@Composable
fun Related(
    state: RelatedItem,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row {
            Image(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .preferredSize(32.dp),
                bitmap = imageResource(state.icon),
                contentDescription = null,
            )
            Column {
                Text(
                    text = state.title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = state.subTitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = state.type,
            )
            Icon(Icons.Filled.ArrowBack, null)
        }
    }
}

//private val MinImageSize = 25.dp
//private val CategoryShape = RoundedCornerShape(10.dp)
//private const val CategoryTextProportion = 0.55f

//@Composable
//fun Related(
//    relatedItem: RelatedItem,
//    gradient: List<Color>,
//    modifier: Modifier = Modifier
//) {
//    Layout(
//        modifier = modifier
//            .aspectRatio(1.45f)
////            .shadow(elevation = 3.dp, shape = CategoryShape)
//            .clip(CategoryShape)
////            .background(Brush.horizontalGradient(gradient))
//            .clickable { /* todo */ },
//        content = {
//            Text(
//                text = relatedItem.title,
//                style = MaterialTheme.typography.subtitle1,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .padding(start = 8.dp)
//            )
//            Image(
//                bitmap = imageResource(R.drawable.ergonomic),
//                modifier = Modifier.fillMaxSize()
//            )
//            Text(
//                text = relatedItem.title,
//                style = MaterialTheme.typography.subtitle1,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .padding(start = 8.dp)
//            )
//            Text(
//                text = relatedItem.title,
//                style = MaterialTheme.typography.subtitle1,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .padding(start = 8.dp)
//            )
//
//        }
//    ) { measurables, constraints ->
//        // Text given a set proportion of width (which is determined by the aspect ratio)
//        val textWidth = (constraints.maxWidth - MinImageSize.toIntPx())
//        val textPlaceable = measurables[0].measure(Constraints.fixedWidth(textWidth))
//
//        // Image is sized to the larger of height of item, or a minimum value
//        // i.e. may appear larger than item (but clipped to the item bounds)
//        val imageSize = MinImageSize.toIntPx()
//        val imagePlaceable = measurables[1].measure(Constraints.fixed(imageSize, imageSize))
//
//        val descriptionPlaceable = measurables[2].measure(Constraints.fixedWidth(textWidth))
//
//        val typePlaceable = measurables[3].measure(Constraints.fixedWidth(textWidth))
//
//        layout(
//            width = constraints.maxWidth,
//            height = constraints.minHeight
//        ) {
//            textPlaceable.place(
//                x = MinImageSize.toIntPx(),
//                y = 0
//            )
//            imagePlaceable.place(
//                // image is placed to end of text i.e. will overflow to the end (but be clipped)
//                x = 0,
//                y = 0
//            )
//            descriptionPlaceable.place(
//                x = MinImageSize.toIntPx(),
//                y = textPlaceable.height
//            )
//            typePlaceable.place(
//                x = 0,
//                y = constraints.maxHeight - typePlaceable.height
//            )
//        }
//    }
//}


//@Composable
//fun Related(
//    state: RelatedItem,
//    modifier: Modifier = Modifier,
//) {
//    ConstraintLayout(modifier = modifier.padding(4.dp)) {
//        val (title, description, image, type) = createRefs()
//
//        Image(
//            modifier = Modifier
//                .padding(4.dp)
//                .constrainAs(image) {
//                    start.linkTo(parent.start)
//                    top.linkTo(parent.top)
//                }.preferredSize(24.dp),
//            bitmap = imageResource(state.icon),
//        )
//
//        Text(
//            text = state.title,
//            modifier = Modifier.constrainAs(title) {
//                start.linkTo(image.end)
//                end.linkTo(parent.end)
//            },
//            fontWeight = FontWeight.Bold
//        )
//
//        Text(
//            text = state.subTitle,
//            maxLines = 2,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier.constrainAs(description) {
//                start.linkTo(title.start, )
//                top.linkTo(title.bottom)
//                end.linkTo(parent.end)
//            }
//        )
//
//        Text(
//            text = state.type,
//            modifier = Modifier.constrainAs(type) {
//                start.linkTo(parent.start)
//                top.linkTo(description.bottom)
//            }
//        )
//    }
//}

private fun createTestImage(): ImageBitmap {
    val imageAsset = ImageBitmap(100, 100)
    Canvas(imageAsset).drawCircle(
        Offset(50.0f, 50.0f), 50.0f,
        Paint().apply { this.color = Color.Cyan }
    )
    return imageAsset
}

@Preview
@Composable
fun previewRelated() {
    val relatedItem = RelatedItem(
        action = {},
        icon = R.drawable.abc_action_bar_item_background_material,
        title = "title",
        subTitle = "subtitle",
        type = "type",
    )

//    Related(
//        relatedItem = relatedItem,
//        gradient = listOf(Color.LightGray, Color.LightGray)
//    )
}