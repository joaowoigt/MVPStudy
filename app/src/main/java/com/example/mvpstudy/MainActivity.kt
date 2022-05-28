package com.example.mvpstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.mvpstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navControler by lazy {
        findNavController(R.id.nav_graph)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}