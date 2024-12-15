package com.example.neuronest


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.neuronest.ui.theme.NeuroNestTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.contentColorFor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuroNestTheme {
                NeuroDiagnosisScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeuroDiagnosisScreen() {
    var isExploring by remember { mutableStateOf(false) }
    var selectedImage by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Neuro Diagnosis") }) },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (!isExploring) {
                StartExploringScreen(onStartClick = { isExploring = true })
            } else if (selectedImage == null) {
                ImageGrid(onImageClick = { selectedImage = it })
            } else {
                SelectedImageScreen(
                    imageRes = selectedImage!!,
                    onNext = { selectedImage = getNextImage(selectedImage!!) },
                    onBack = { selectedImage = null }
                )
            }
        }
    }
}

@Composable
fun StartExploringScreen(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onStartClick, modifier = Modifier.size(200.dp)) {
            Text(
                text = "Start Exploring",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImageGrid(onImageClick: (Int) -> Unit) {
    val images = listOf(
        Pair( R.drawable.i1, "description"),
        Pair( R.drawable.i2, "description"),
        Pair( R.drawable.i3, "description"),
        Pair( R.drawable.i4, "description"),Pair( R.drawable.i5, "description"),Pair( R.drawable.i6, "description"),
        Pair( R.drawable.i7, "description"),Pair( R.drawable.i8, "description"),Pair( R.drawable.i9, "description"),
        Pair( R.drawable.i10, "description"),Pair( R.drawable.i11, "description"),Pair( R.drawable.i12, "description"),
        Pair( R.drawable.i13, "description"), Pair( R.drawable.i14, "description"),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(images.size) { index ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation =  CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .size(150.dp)
                        .clickable { onImageClick(images[index].first) }
                ) {
                    Image(
                        painter = painterResource(id = images[index].first),
                        contentDescription = images[index].second,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = images[index].second,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SelectedImageScreen(imageRes: Int, onNext: () -> Unit, onBack: () -> Unit) {
    val descriptions = mapOf(
        R.drawable.i1 to "description",R.drawable.i2 to "description",R.drawable.i3 to "description",
        R.drawable.i4 to "description",R.drawable.i5 to "description",R.drawable.i6 to "description",
        R.drawable.i7 to "description",R.drawable.i8 to "description",R.drawable.i9 to "description",
        R.drawable.i10 to "description",R.drawable.i11 to "description",R.drawable.i12 to "description",
        R.drawable.i13 to "description",R.drawable.i14 to "description",
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = descriptions[imageRes],
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = descriptions[imageRes] ?: "",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onBack) {
                    Text("Go Back")
                }
                Button(onClick = onNext) {
                    Text("Next Image")
                }
            }
        }
    }
}
fun getNextImage(currentImage: Int): Int {
    val images = listOf(
        R.drawable.i1, R.drawable.i2, R.drawable.i3,
        R.drawable.i4,R.drawable.i5,R.drawable.i6,
        R.drawable.i7,R.drawable.i8,R.drawable.i9,
        R.drawable.i10,R.drawable.i11,R.drawable.i12,
        R.drawable.i13,R.drawable.i14,
    )
    val currentIndex = images.indexOf(currentImage)
    return if (currentIndex < images.size - 1) images[currentIndex + 1] else images[0]
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = BottomAppBarDefaults.containerColor,
        contentColor = contentColorFor(containerColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("Home", color = Color.White, modifier = Modifier.clickable { /* Handle Click */ })
            Text("Settings", color = Color.White, modifier = Modifier.clickable { /* Handle Click */ })
            Text("Help", color = Color.White, modifier = Modifier.clickable { /* Handle Click */ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNeuroDiagnosisScreen() {

    NeuroDiagnosisScreen()

}

