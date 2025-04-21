package com.example.neuronest.Task2.Task2Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.neuronest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchGameScreen(onBackClick: () -> Unit = {}, navController: NavHostController) {
    val matchPairs = setOf(
        setOf("1", "4"),
        setOf("3", "2"),
        setOf("5", "8"),
        setOf("7", "6"),
        setOf("9", "12"),
        setOf("11", "10"),
        setOf("13", "16"),
        setOf("15", "14"),
        setOf("17", "20"),
        setOf("19", "18")
    )

    val questions = listOf(
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("1", imageRes = R.drawable.class1),
                MatchOption("2", text = "YES"),
                MatchOption("3", imageRes = R.drawable.class2),
                MatchOption("4", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("5", imageRes = R.drawable.class3),
                MatchOption("6", text = "YES"),
                MatchOption("7", imageRes = R.drawable.class4),
                MatchOption("8", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("9", imageRes = R.drawable.class5),
                MatchOption("10", text = "YES"),
                MatchOption("11", imageRes = R.drawable.class6),
                MatchOption("12", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("13", imageRes = R.drawable.class7),
                MatchOption("14", text = "YES"),
                MatchOption("15", imageRes = R.drawable.class8),
                MatchOption("16", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("17", imageRes = R.drawable.class9),
                MatchOption("18", text = "YES"),
                MatchOption("19", imageRes = R.drawable.class10),
                MatchOption("20", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("21", imageRes = R.drawable.class11),
                MatchOption("22", text = "YES"),
                MatchOption("23", imageRes = R.drawable.class12),
                MatchOption("24", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("25", imageRes = R.drawable.class13),
                MatchOption("26", text = "YES"),
                MatchOption("27", imageRes = R.drawable.class14),
                MatchOption("28", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("29", imageRes = R.drawable.class15),
                MatchOption("30", text = "YES"),
                MatchOption("31", imageRes = R.drawable.class16),
                MatchOption("32", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("33", imageRes = R.drawable.class17),
                MatchOption("34", text = "YES"),
                MatchOption("35", imageRes = R.drawable.class18),
                MatchOption("36", text = "NO")
            )
        ),
        MatchQuestion(
            "Match the pair of images and texts",
            listOf(
                MatchOption("37", imageRes = R.drawable.class19),
                MatchOption("38", text = "YES"),
                MatchOption("39", imageRes = R.drawable.class20),
                MatchOption("40", text = "NO")
            )
        )
    )
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var firstSelected by remember { mutableStateOf<MatchOption?>(null) }
    var secondSelected by remember { mutableStateOf<MatchOption?>(null) }
    var matchResult by remember { mutableStateOf<String?>(null) }
    var resetTrigger by remember { mutableStateOf(false) }
    val currentQuestion = questions[currentQuestionIndex]

    LaunchedEffect(key1 = resetTrigger) {
        if (firstSelected != null && secondSelected != null) {
            kotlinx.coroutines.delay(1500)
            firstSelected = null
            secondSelected = null
            matchResult = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Matching") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = currentQuestion.questionText,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            GridOfImages(currentQuestion.options, firstSelected, secondSelected) { selected ->
                if (firstSelected == null) {
                    firstSelected = selected
                    matchResult = null
                } else if (secondSelected == null && selected != firstSelected) {
                    secondSelected = selected
                    val isMatch = matchPairs.contains(setOf(firstSelected!!.id, selected.id))
                    matchResult = if (isMatch) "Matched!" else "Try Again!"
                    resetTrigger = !resetTrigger
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            matchResult?.let {
                Text(
                    text = it,
                    color = if (it == "Matched!") Color.Green else Color.Red,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (currentQuestionIndex > 0) currentQuestionIndex--
                        firstSelected = null
                        secondSelected = null
                        matchResult = null
                    },
                    enabled = currentQuestionIndex > 0,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Previous")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        if (currentQuestionIndex < questions.size - 1) currentQuestionIndex++
                        firstSelected = null
                        secondSelected = null
                        matchResult = null
                    },
                    enabled = currentQuestionIndex < questions.size - 1,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Next")
                }
            }
        }
    }
}


@Composable
fun GridOfImages(
    options: List<MatchOption>,
    firstSelected: MatchOption?,
    secondSelected: MatchOption?,
    onSelectImage: (MatchOption) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxWidth()) {
        items(options) { option ->
            val isSelected = option == firstSelected || option == secondSelected
            MatchImage(option, isSelected) {
                onSelectImage(option)
            }
        }
    }
}

@Composable
fun MatchImage(option: MatchOption, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(20.dp)
    ) {
        option.imageRes?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = option.text ?: option.id,
                modifier = Modifier.size(140.dp)
            )
        }
        option.text?.let {
            Text(text = it, style = MaterialTheme.typography.titleMedium)
        }
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color.Green
            )
        }
    }
}

data class MatchOption(
    val id: String,
    val text: String? = null,
    val imageRes: Int? = null
)

data class MatchQuestion(
    val questionText: String,
    val options: List<MatchOption>
)

