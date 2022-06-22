package com.example.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customButtonActivity.setUpText("Customized top text")
        binding.customButtonActivity.setDowntext("Customized down text")
    }
}