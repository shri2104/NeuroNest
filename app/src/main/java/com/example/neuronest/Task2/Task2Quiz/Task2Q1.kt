import android.graphics.Rect
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.example.neuronest.R

data class BehaviorImage(val id: Int, val imageRes: Int)

object BehaviorImageSamples {
    val sampleList = listOf(
        BehaviorImage(1, R.drawable.m1b1),
        BehaviorImage(2, R.drawable.m1b2),
        BehaviorImage(3, R.drawable.m1g3),
        BehaviorImage(4, R.drawable.m1b3)
    )
}

@Composable
fun DropBox(
    label: String,
    color: Color,
    imageList: List<BehaviorImage>,
    modifier: Modifier = Modifier,
    onPositioned: (androidx.compose.ui.geometry.Rect) -> Unit = {}
) {
    Column(
        modifier = modifier
            .height(150.dp)
            .border(2.dp, Color.Gray)
            .background(color)
            .padding(8.dp)
            .onGloballyPositioned { coordinates ->
                onPositioned(coordinates.boundsInWindow())
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 16.sp)
        Row {
            imageList.forEach {
                Image(
                    painter = painterResource(id = it.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun BehaviorClassificationScreen() {
    val allImages = remember { BehaviorImageSamples.sampleList }
    val unplacedImages = remember { mutableStateListOf<BehaviorImage>().apply { addAll(allImages) } }
    val goodImages = remember { mutableStateListOf<BehaviorImage>() }
    val badImages = remember { mutableStateListOf<BehaviorImage>() }
    var goodBoxBounds by remember { mutableStateOf<androidx.compose.ui.geometry.Rect?>(null) }
    var badBoxBounds by remember { mutableStateOf<androidx.compose.ui.geometry.Rect?>(null) }

    var draggedImage by remember { mutableStateOf<BehaviorImage?>(null) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Classify the Behaviors",
            fontSize = 22.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            DropBox(
                label = "👍 Good",
                color = Color.Green.copy(alpha = 0.2f),
                imageList = goodImages,
                modifier = Modifier.weight(1f),
                onPositioned = { goodBoxBounds = it }
            )

            DropBox(
                label = "👎 Bad",
                color = Color.Red.copy(alpha = 0.2f),
                imageList = badImages,
                modifier = Modifier.weight(1f),
                onPositioned = { badBoxBounds = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Drag from below:", fontSize = 18.sp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // adjust as needed
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(unplacedImages, key = { it.id }) { image ->
                    if (draggedImage?.id != image.id) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .padding(8.dp)
                                .zIndex(0f)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = { offset ->
                                            draggedImage = image
                                            dragOffset = offset
                                        },
                                        onDrag = { change, _ ->
                                            change.consume()
                                            dragOffset = change.position
                                        },
                                        onDragEnd = {
                                            val imageCenter = dragOffset
                                            draggedImage?.let { img ->
                                                when {
                                                    goodBoxBounds?.contains(imageCenter) == true -> {
                                                        unplacedImages.remove(img)
                                                        goodImages.add(img)
                                                    }
                                                    badBoxBounds?.contains(imageCenter) == true -> {
                                                        unplacedImages.remove(img)
                                                        badImages.add(img)
                                                    }
                                                    else -> {
                                                        // no drop
                                                    }
                                                }
                                            }
                                            draggedImage = null
                                            dragOffset = Offset.Zero
                                        },
                                        onDragCancel = {
                                            draggedImage = null
                                            dragOffset = Offset.Zero
                                        }
                                    )
                                }
                        ) {
                            Image(
                                painter = painterResource(id = image.imageRes),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // Floating image while dragging
            draggedImage?.let { img ->
                Image(
                    painter = painterResource(id = img.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .offset {
                            IntOffset(dragOffset.x.toInt() - 40, dragOffset.y.toInt() - 40)
                        }
                        .zIndex(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBehaviorClassificationScreen() {
    BehaviorClassificationScreen()
}
