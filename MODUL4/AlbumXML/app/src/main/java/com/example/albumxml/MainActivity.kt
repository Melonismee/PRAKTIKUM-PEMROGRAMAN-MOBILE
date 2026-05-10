package com.example.albumxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.albumxml.viewmodel.AlbumViewModel
import com.example.albumxml.viewmodel.AlbumViewModelFactory

class MainActivity : AppCompatActivity() {

    val viewModel: AlbumViewModel by viewModels {
        AlbumViewModelFactory("Ibnu")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
    }
}