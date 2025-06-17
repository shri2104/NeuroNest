package com.example.neuronest.Task2.Task2Quiz
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragAndDropQuestionScreen1(
    questionImage: Int,
    optionTexts: List<String>,
    correctTextIndex: Int,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    dropThresholdY: Float,
    onAnswerCorrect: () -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val offsets = remember { mutableStateListOf(*Array(optionTexts.size) { Offset.Zero }) }

    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var droppedTextIndex by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(currentQuestionNumber) {
        for (i in offsets.indices) {
            offsets[i] = Offset.Zero
        }
        isAnswerCorrect = false
        droppedTextIndex = null
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
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onPreviousQuestion,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Previous")
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Next")
                }
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val isTablet = maxWidth > 600.dp
            val imageSize = if (isTablet) 380.dp else 260.dp
            val optionBoxSize = if (isTablet) 160.dp else 110.dp
            val dropBoxHeight = if (isTablet) 240.dp else 160.dp
            val fontSize = if (isTablet) 24.sp else MaterialTheme.typography.bodyLarge.fontSize

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Question $currentQuestionNumber of $totalQuestions",
                    fontSize = fontSize,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = questionImage),
                    contentDescription = "Question Image",
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    optionTexts.forEachIndexed { index, text ->
                        Box(
                            modifier = Modifier
                                .size(optionBoxSize)
                                .offset {
                                    IntOffset(
                                        offsets[index].x.toInt(),
                                        (offsets[index].y - scrollState.value).toInt()
                                    )
                                }
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                                .then(
                                    if (!isAnswerCorrect)
                                        Modifier.pointerInput(Unit) {
                                            detectDragGestures(
                                                onDragStart = {
                                                    draggedItemIndex = index
                                                },
                                                onDrag = { change, dragAmount ->
                                                    change.consume()
                                                    offsets[index] += Offset(
                                                        dragAmount.x,
                                                        dragAmount.y
                                                    )
                                                },
                                                onDragEnd = {
                                                    val dropY = offsets[index].y + scrollState.value
                                                    if (!isAnswerCorrect && index == correctTextIndex && dropY > dropThresholdY) {
                                                        isAnswerCorrect = true
                                                        droppedTextIndex = index
                                                        Toast.makeText(
                                                            context,
                                                            "Correct Answer!",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
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
                            Text(
                                text = text,
                                fontSize = fontSize,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))

                Divider(
                    color = Color.Gray,
                    thickness = 1.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dropBoxHeight)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isAnswerCorrect) Color(0xFF81C784) else Color(0xFFD6D6D6)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isAnswerCorrect && droppedTextIndex != null) {
                        Text(
                            text = optionTexts[droppedTextIndex!!],
                            fontSize = fontSize,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        Text(
                            text = "Drop Answer Here",
                            fontSize = fontSize,
                            color = Color.DarkGray
                        )
                    }
                }

            }
        }
    }
}