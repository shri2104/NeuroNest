package com.example.neuronest.GoodandBadtouch

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragAndDropQuestionScreen(
    questionImage: Int,
    optionImages: List<Int>,
    correctImageIndex: Int,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    dropThresholdY: Float,
    onAnswerCorrect: () -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit
) {
    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    val offsets = remember { mutableStateListOf(*Array(optionImages.size) { Offset(0f, 0f)})}
    val scrollState = rememberScrollState()
    var droppedImageIndex by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
    LaunchedEffect(currentQuestionNumber) {
        offsets.forEachIndexed { index, _ ->
            offsets[index] = Offset(0f, 0f)
        }
        isAnswerCorrect = false
        droppedImageIndex = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Drag And Drop")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F51B5), titleContentColor = Color.White)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Question $currentQuestionNumber of $totalQuestions",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Image(
                    painter = painterResource(id = questionImage),
                    contentDescription = "Question Image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    optionImages.chunked(2).forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            row.forEachIndexed { indexInRow, imageResId ->
                                val index = optionImages.indexOf(imageResId)
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .offset {
                                            IntOffset(
                                                offsets[index].x.toInt(),
                                                (offsets[index].y - scrollState.value).toInt()
                                            )
                                        }
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray)
                                        .pointerInput(Unit) {
                                            detectDragGestures(
                                                onDragStart = {
                                                    draggedItemIndex = index
                                                },
                                                onDrag = { change, dragAmount ->
                                                    change.consume()
                                                    offsets[index] = offsets[index] + Offset(dragAmount.x, dragAmount.y)
                                                    if (isAnswerCorrect && offsets[correctImageIndex].y <= dropThresholdY) {
                                                        isAnswerCorrect = false
                                                    }
                                                },
                                                onDragEnd = {
                                                    if (draggedItemIndex == correctImageIndex && offsets[index].y > dropThresholdY + scrollState.value) {
                                                        isAnswerCorrect = true
                                                        Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT).show()
                                                        droppedImageIndex = index
                                                        onAnswerCorrect()
                                                    } else {
                                                        offsets[index] = Offset(0f, 0f)
                                                    }
                                                    draggedItemIndex = null
                                                },
                                                onDragCancel = {
                                                    offsets[index] = Offset(0f, 0f)
                                                }
                                            )
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = imageResId),
                                        contentDescription = "Option $index",
                                        modifier = Modifier.size(80.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(
                    color = Color.Gray,
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isAnswerCorrect) Color.Green else Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    if (droppedImageIndex != null && isAnswerCorrect) {
                        Image(
                            painter = painterResource(id = optionImages[droppedImageIndex!!]),
                            contentDescription = "Dropped Image",
                            modifier = Modifier.size(80.dp)

                        )
                    } else {
                        Text(
                            text = "Drop Answer Here",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onPreviousQuestion,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                    ) {
                        Text("Previous")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onNextQuestion,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}