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
import org.w3c.dom.Text
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
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val offsets = remember { mutableStateListOf(*Array(optionImages.size) { Offset.Zero }) }

    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var droppedImageIndex by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(currentQuestionNumber) {
        // Reset state for new question
        for (i in offsets.indices) {
            offsets[i] = Offset.Zero
        }
        isAnswerCorrect = false
        droppedImageIndex = null
        draggedItemIndex = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drag And Drop") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = questionImage),
                    contentDescription = "Question Image",
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                optionImages.chunked(2).forEach { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        row.forEach { imageResId ->
                            val index = optionImages.indexOf(imageResId)
                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .offset {
                                        IntOffset(
                                            offsets[index].x.toInt(),
                                            (offsets[index].y - scrollState.value).toInt()
                                        )
                                    }
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray)
                                    // Only allow drag if answer is not yet correct
                                    .then(
                                        if (!isAnswerCorrect)
                                            Modifier.pointerInput(Unit) {
                                                detectDragGestures(
                                                    onDragStart = {
                                                        draggedItemIndex = index
                                                    },
                                                    onDrag = { change, dragAmount ->
                                                        change.consume()
                                                        offsets[index] += Offset(dragAmount.x, dragAmount.y)
                                                    },
                                                    onDragEnd = {
                                                        val dropY = offsets[index].y + scrollState.value
                                                        if (!isAnswerCorrect && index == correctImageIndex && dropY > dropThresholdY) {
                                                            isAnswerCorrect = true
                                                            droppedImageIndex = index
                                                            Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT).show()
                                                            onAnswerCorrect()
                                                        } else {
                                                            offsets[index] = Offset.Zero
                                                        }
                                                        draggedItemIndex = null
                                                    },
                                                    onDragCancel = {
                                                        offsets[index] = Offset.Zero
                                                    }
                                                )
                                            }
                                        else Modifier
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = "Option $index",
                                    modifier = Modifier.size(120.dp)
                                )
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
                        .background(if (isAnswerCorrect) Color(0xFF81C784) else Color(0xFFD6D6D6)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isAnswerCorrect && droppedImageIndex != null) {
                        Image(
                            painter = painterResource(id = optionImages[droppedImageIndex!!]),
                            contentDescription = "Dropped Image",
                            modifier = Modifier.size(120.dp)
                        )
                    } else {
                        Text(
                            text = "Drop Answer Here",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onPreviousQuestion,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Text("Previous")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onNextQuestion,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}
