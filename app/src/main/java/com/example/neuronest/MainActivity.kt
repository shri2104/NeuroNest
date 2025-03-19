package com.example.neuronest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.neuronest.Navigation.Navigation
import com.example.neuronest.retrofit.createApiService
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        val apiService = createApiService()
        enableEdgeToEdge()
        setContent {
            Navigation(apiService)
        }
    }
}

