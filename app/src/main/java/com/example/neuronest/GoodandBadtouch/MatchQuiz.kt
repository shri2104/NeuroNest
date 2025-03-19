package com.example.neuronest.GoodandBadtouch

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.neuronest.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchThePairQuestionScreen(
    question: Question,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    onAnswerCorrect: () -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit
) {
    var selectedLeftId by remember { mutableStateOf<Int?>(null) }
    var matchedPairs by remember { mutableStateOf<Map<Int, Boolean>>(emptyMap()) }
    var incorrectPair by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    val shuffledRightPairs = remember { question.pairs.shuffled() }
    val context = LocalContext.current
    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener { release() }
        }
    }
    LaunchedEffect(incorrectPair) {
        incorrectPair?.let {
            delay(1000)
            matchedPairs = matchedPairs - it.first - it.second
            incorrectPair = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Match the Pair") },
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
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display question number
                Text(
                    text = "Question $currentQuestionNumber of $totalQuestions",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column {
                    question.pairs.forEachIndexed { index, pair ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        when {
                                            selectedLeftId == pair.id -> Color.Yellow
                                            matchedPairs[pair.id] == true -> Color.Green
                                            incorrectPair?.first == pair.id -> Color.Red
                                            else -> Color.LightGray
                                        }, RoundedCornerShape(8.dp)
                                    )
                                    .padding(16.dp)
                                    .clickable {
                                        selectedLeftId = pair.id
                                        if (pair.leftItem == "GoodTouch") {
                                            playSound(R.raw.good_touch)
                                        } else if (pair.leftItem == "BadTouch") {
                                            playSound(R.raw.bad_touch)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = pair.leftItem)
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            val shuffledPair = shuffledRightPairs[index]
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        when {
                                            matchedPairs[shuffledPair.id] == true -> Color.Green
                                            incorrectPair?.second == shuffledPair.id -> Color.Red
                                            else -> Color.LightGray
                                        }, RoundedCornerShape(8.dp)
                                    )
                                    .padding(16.dp)
                                    .clickable {
                                        selectedLeftId?.let { leftId ->
                                            val isCorrect = question.correctMatches[leftId] == shuffledPair.id
                                            if (isCorrect) {
                                                matchedPairs = matchedPairs + (leftId to true) + (shuffledPair.id to true)
                                                onAnswerCorrect()
                                            } else {
                                                incorrectPair = Pair(leftId, shuffledPair.id)
                                                matchedPairs = matchedPairs + (leftId to false) + (shuffledPair.id to false)
                                            }
                                        }
                                        selectedLeftId = null
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = shuffledPair.rightItem),
                                    contentDescription = null,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Navigation buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = onPreviousQuestion, colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))) {
                        Text("Previous")
                    }
                    Button(onClick = onNextQuestion, colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))) {
                        Text("Next")
                    }
                }
            }
        }
    }
}
